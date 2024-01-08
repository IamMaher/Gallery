package com.example.myapplication.feature.presentation.main

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.feature.data.datastore.getDarkTheme
import com.example.myapplication.feature.data.datastore.isSystemDarkTheme
import com.example.myapplication.BaseApplication
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(app: BaseApplication) : ViewModel() {
    private val darkThemeFlow = getDarkTheme(app)
    var darkTheme by mutableStateOf(isSystemDarkTheme(app))

    init {
        // Collect theme change
        viewModelScope.launch {
            darkThemeFlow.collectLatest { darkTheme = it }
        }
    }
}