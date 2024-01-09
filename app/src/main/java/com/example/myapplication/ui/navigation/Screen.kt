package com.example.myapplication.ui.navigation

sealed class Screen(val route: String) {
    object AlbumScreen : Screen("album")

}
