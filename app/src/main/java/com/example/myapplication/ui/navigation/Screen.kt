package com.example.gallery.ui.navigation

import com.example.gallery.feature.domain.model.Album
import com.google.gson.Gson
import java.net.URLEncoder

sealed class Screen(val route: String) {
    object AlbumScreen : Screen("album")

}
