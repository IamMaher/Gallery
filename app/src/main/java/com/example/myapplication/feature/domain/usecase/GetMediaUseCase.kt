package com.example.myapplication.feature.domain.usecase

import com.example.gallery.feature.domain.model.Media
import com.example.myapplication.core.Resource
import com.example.myapplication.core.UseCase
import com.example.myapplication.feature.domain.MediaRepository
import javax.inject.Inject

class GetMediaUseCase @Inject constructor(private val repository: MediaRepository) : UseCase<List<Media>, UseCase.None>() {
    override suspend fun run(params: None): Resource<List<Media>> = repository.getMedia()
}