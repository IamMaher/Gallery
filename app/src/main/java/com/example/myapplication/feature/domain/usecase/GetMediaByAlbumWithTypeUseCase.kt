package com.example.myapplication.feature.domain.usecase

import com.example.gallery.feature.domain.model.Media
import com.example.myapplication.core.Resource
import com.example.myapplication.core.UseCase
import com.example.myapplication.feature.domain.MediaRepository
import com.example.myapplication.feature.presentation.album.AllowedMedia
import javax.inject.Inject


class GetMediaByAlbumWithTypeUseCase @Inject constructor(private val repository: MediaRepository) : UseCase<List<Media>, GetMediaByAlbumWithTypeUseCase.Params>() {
    override suspend fun run(params: Params): Resource<List<Media>> = repository.getMediaByAlbumIdWithType(params.albumId, params.type)
    data class Params(val albumId: Long, val type: AllowedMedia)
}