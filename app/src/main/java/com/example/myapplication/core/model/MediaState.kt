package com.example.myapplication.core.model

import android.os.Parcelable
import androidx.compose.runtime.Immutable
import com.example.gallery.feature.domain.model.Album
import com.example.gallery.feature.domain.model.Media
import kotlinx.parcelize.Parcelize

@Immutable
@Parcelize
data class MediaState(
    val media: List<Media> = emptyList(),
    val error: String = "",
    val isLoading: Boolean = true
) : Parcelable


@Immutable
@Parcelize
data class AlbumState(
    val albums: List<Album> = emptyList(),
    val error: String = ""
) : Parcelable