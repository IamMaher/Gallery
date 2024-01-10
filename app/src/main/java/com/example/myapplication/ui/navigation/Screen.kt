package com.example.myapplication.ui.navigation

import com.example.gallery.feature.domain.model.Album
import com.google.gson.Gson
import java.net.URLEncoder

sealed class Screen(val route: String) {
    object AlbumScreen : Screen("album")
    object MediaScreen : Screen("media/{album}") {
        fun routeWithArgs(album: Album): String {
            return "media/${URLEncoder.encode(Gson().toJson(album),"UTF-8")}"
        }
    }
}
