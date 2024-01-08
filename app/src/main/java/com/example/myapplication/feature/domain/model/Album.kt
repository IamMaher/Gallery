package com.example.gallery.feature.domain.model

import android.os.Parcelable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.toLowerCase
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize

@Immutable
@Parcelize
data class Album(
    val id: Long = 0,
    val label: String,
    val pathToThumbnail: String,
    val relativePath: String,
    var timestamp: Long,
    var count: Long = 0
) : Parcelable {

    @IgnoredOnParcel
    val volume: String = pathToThumbnail.substringBeforeLast("/").removeSuffix(relativePath.removeSuffix("/"))

    @IgnoredOnParcel
    val isOnSdcard: Boolean = volume.toLowerCase(Locale.current).matches(".*[0-9a-f]{4}-[0-9a-f]{4}".toRegex())

    companion object {
        val NewAlbum = Album(
            id = -200,
            label = "New Album",
            pathToThumbnail = "",
            relativePath = "",
            timestamp = 0,
            count = 0,
        )

        fun allPhotos(
            pathToThumbnail: String = "",
            relativePath: String = "",
            count: Long = 0
        ) = Album(id = 0, label = "All Photos", pathToThumbnail = pathToThumbnail, relativePath = relativePath, timestamp = 0, count = count)

        fun allVideos(
            pathToThumbnail: String = "",
            relativePath: String = "",
            count: Long = 0
        ) = Album(id = 1, label = "All Videos", pathToThumbnail = pathToThumbnail, relativePath = relativePath, timestamp = 0, count = count)
    }
}