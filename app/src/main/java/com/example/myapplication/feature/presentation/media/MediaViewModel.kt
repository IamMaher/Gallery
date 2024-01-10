package com.example.gallery.feature.presentation.media

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gallery.feature.domain.model.Album
import com.example.gallery.feature.domain.model.Media

import com.example.myapplication.BaseApplication
import com.example.myapplication.core.Resource
import com.example.myapplication.core.model.MediaState
import com.example.myapplication.feature.data.datastore.getSortOrder
import com.example.myapplication.feature.data.datastore.saveSortOrder
import com.example.myapplication.feature.domain.usecase.GetMediaByAlbumUseCase
import com.example.myapplication.feature.domain.usecase.GetMediaByTypeUseCase
import com.example.myapplication.feature.presentation.album.AllowedMedia
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.net.URLDecoder
import javax.inject.Inject

@HiltViewModel
class MediaViewModel @Inject constructor(
    getMediaByTypeUseCase: GetMediaByTypeUseCase,
    getMediaByAlbumUseCase: GetMediaByAlbumUseCase,
    private val savedStateHandle: SavedStateHandle,
    private val app: BaseApplication,
) : ViewModel() {
    val album: Album

    private val sortOrderFlow: Flow<SortOrder> = getSortOrder(app)
    var sortOrder by mutableStateOf(SortOrder.Grid)
        private set

    var screenState by mutableStateOf(savedStateHandle.getStateFlow(SCREEN_STATE_KEY, MediaState()))
        private set

    init {
        val albumJson: String? = URLDecoder.decode(savedStateHandle["album"], "UTF-8")
        album = Gson().fromJson(albumJson, Album::class.java)
        when (album.label) {
            Album.allPhotos().label -> getMediaByTypeUseCase(viewModelScope, GetMediaByTypeUseCase.Params(AllowedMedia.PHOTOS), ::updateScreenState)
            Album.allVideos().label -> getMediaByTypeUseCase(viewModelScope, GetMediaByTypeUseCase.Params(AllowedMedia.VIDEOS), ::updateScreenState)
            else -> getMediaByAlbumUseCase(viewModelScope, GetMediaByAlbumUseCase.Params(album.id), ::updateScreenState)
        }

        viewModelScope.launch {
            sortOrderFlow.collectLatest {
                if (it != sortOrder) {
                    sortOrder = it
                    updateScreenState(currentScreenState())
                }
            }
        }
    }


    fun onSortOrderChange(sortOrder: SortOrder) {
        viewModelScope.launch {
            saveSortOrder(app, sortOrder)
            updateScreenState(currentScreenState())
        }
    }

    private fun updateScreenState(screenState: Resource<List<Media>>) {
        val currentState = currentScreenState()
        val newState = when (screenState) {
            is Resource.Success -> currentState.copy(media = screenState.data)
            is Resource.Error -> currentState.copy(error = "Something Went Wrong")
        }
        updateScreenState(newState)
    }

    private fun currentScreenState(): MediaState {
        return savedStateHandle[SCREEN_STATE_KEY] ?: MediaState()
    }

    private fun updateScreenState(newState: MediaState) {
        savedStateHandle[SCREEN_STATE_KEY] = newState
    }

    private companion object {
        private const val SCREEN_STATE_KEY = "mediaScreenState"
    }
}