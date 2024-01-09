package com.example.myapplication.feature.domain.usecase

import android.net.Uri
import com.example.gallery.feature.domain.model.Media
import com.example.myapplication.core.Resource
import com.example.myapplication.core.UseCase
import com.example.myapplication.feature.domain.MediaRepository
import javax.inject.Inject

class GetMediaListByUrisUseCase @Inject constructor(private val repository: MediaRepository) : UseCase<List<Media>, GetMediaListByUrisUseCase.Params>() {
    override suspend fun run(params: Params): Resource<List<Media>> = repository.getMediaListByUris(params.listOfUris, params.reviewMode)
    data class Params(val listOfUris: List<Uri>, val reviewMode: Boolean)
}