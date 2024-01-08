package com.example.myapplication.feature.presentation.album

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gallery.feature.data.datastore.toggleDarkTheme
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlbumViewModel @Inject constructor() : ViewModel() {

    fun toggleDarkMode(app: Context) {
        viewModelScope.launch { toggleDarkTheme(app) }
    }
}