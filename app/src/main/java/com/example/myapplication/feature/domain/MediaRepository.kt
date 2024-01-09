package com.example.myapplication.feature.domain

import android.net.Uri
import com.example.gallery.feature.domain.model.Album
import com.example.gallery.feature.domain.model.Media
import com.example.myapplication.core.Resource
import com.example.myapplication.feature.presentation.album.AllowedMedia


interface MediaRepository {
    fun getMedia(): Resource<List<Media>>

    fun getMediaByType(allowedMedia: AllowedMedia): Resource<List<Media>>

    fun getAlbums(): Resource<List<Album>>

    fun getMediaById(mediaId: Long): Media?

    fun getMediaByAlbumId(albumId: Long): Resource<List<Media>>

    fun getMediaByAlbumIdWithType(albumId: Long, allowedMedia: AllowedMedia): Resource<List<Media>>

    fun getAlbumsWithType(allowedMedia: AllowedMedia): Resource<List<Album>>

    fun getMediaByUri(uriAsString: String): Resource<List<Media>>

    fun getMediaListByUris(listOfUris: List<Uri>, reviewMode: Boolean): Resource<List<Media>>
}