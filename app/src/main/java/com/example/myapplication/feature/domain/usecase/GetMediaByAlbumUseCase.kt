package com.example.myapplication.feature.domain.usecase

import com.example.gallery.feature.domain.model.Media
import com.example.myapplication.core.Resource
import com.example.myapplication.core.UseCase
import com.example.myapplication.feature.domain.MediaRepository
import javax.inject.Inject


class GetMediaByAlbumUseCase @Inject constructor(private val repository: MediaRepository) : UseCase<List<Media>, GetMediaByAlbumUseCase.Params>() {
    override suspend fun run(params: Params): Resource<List<Media>> = repository.getMediaByAlbumId(params.albumId)
    data class Params(val albumId: Long)
}

