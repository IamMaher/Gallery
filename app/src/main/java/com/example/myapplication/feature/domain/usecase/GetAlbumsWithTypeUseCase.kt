package com.example.myapplication.feature.domain.usecase

import com.example.gallery.feature.domain.model.Album
import com.example.myapplication.core.Resource
import com.example.myapplication.core.UseCase
import com.example.myapplication.feature.domain.MediaRepository
import com.example.myapplication.feature.presentation.album.AllowedMedia
import javax.inject.Inject

class GetAlbumsWithTypeUseCase @Inject constructor(private val repository: MediaRepository) : UseCase<List<Album>, GetAlbumsWithTypeUseCase.Params>() {
    override suspend fun run(params: Params): Resource<List<Album>> = repository.getAlbumsWithType(params.allowedMedia)
    data class Params(val allowedMedia: AllowedMedia)
}