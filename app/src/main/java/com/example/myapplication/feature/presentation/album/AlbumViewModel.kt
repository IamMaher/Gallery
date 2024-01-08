package com.example.myapplication.feature.presentation.album

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gallery.feature.domain.model.Album
import com.example.myapplication.core.Resource
import com.example.myapplication.core.UseCase
import com.example.myapplication.core.model.AlbumState
import com.example.myapplication.feature.data.datastore.toggleDarkTheme
import com.example.myapplication.feature.domain.usecase.GetAlbumsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlbumViewModel @Inject constructor(
    getAlbumsUseCase: GetAlbumsUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    var screenState by mutableStateOf(savedStateHandle.getStateFlow(SCREEN_STATE_KEY, AlbumState()))
        private set

    init {
        // Get all albums
        getAlbumsUseCase(viewModelScope, UseCase.None(), ::updateScreenState)
    }

    private fun updateScreenState(albumScreenState: Resource<List<Album>>) {
        val currentState = currentScreenState()
        val newState = when (albumScreenState) {
            is Resource.Success -> currentState.copy(albums = albumScreenState.data)
            is Resource.Error -> currentState.copy(error = "Something Went Wrong")
        }
        updateScreenState(newState)
    }


    fun toggleDarkMode(app: Context) {
        viewModelScope.launch { toggleDarkTheme(app) }
    }

    private fun currentScreenState(): AlbumState {
        return savedStateHandle[SCREEN_STATE_KEY] ?: AlbumState()
    }

    private fun updateScreenState(newState: AlbumState) {
        savedStateHandle[SCREEN_STATE_KEY] = newState
    }

    private companion object {
        private const val SCREEN_STATE_KEY = "albumScreenState"
    }
}