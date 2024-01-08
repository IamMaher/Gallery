package com.example.myapplication.feature.domain

import com.example.gallery.feature.domain.model.Album
import com.example.myapplication.core.Resource
import com.example.myapplication.feature.presentation.album.AllowedMedia

interface MediaRepository {

    fun getAlbums(): Resource<List<Album>>


    fun getAlbumsWithType(allowedMedia: AllowedMedia): Resource<List<Album>>

}