package com.example.myapplication.feature.data.media

import android.content.ContentResolver
import android.net.Uri
import android.provider.MediaStore
import com.example.gallery.feature.domain.model.Media
import com.example.myapplication.feature.data.ContentQuery

fun ContentResolver.getMediaByUri(uri: Uri): Media? {
    var media: Media? = null
    val mediaQuery = ContentQuery.MediaQuery().copy(
        selection = MediaStore.MediaColumns.DATA + "=?",
        selectionArgs = arrayOf(uri.toString()),
    )

    with(query(mediaQuery)) {
        moveToFirst()
        while (!isAfterLast) {
            try {
                media = getMediaFromCursor()
                break
            } catch (e: Exception) {
                close()
                e.printStackTrace()
            }
        }
        moveToNext()
        close()

        if (media == null) {
            media = Media.createFromUri(uri)
        }

        return media
    }
}

fun ContentResolver.getMediaListByUris(list: List<Uri>): List<Media> {
    val mediaList = ArrayList<Media>()
    val mediaQuery = ContentQuery.MediaQuery().copy(
        selection = MediaStore.MediaColumns._ID + "=?",
        selectionArgs = list.map { it.toString().substringAfterLast("/") }.toTypedArray(),
    )
    mediaList.addAll(getMedia(mediaQuery))
    if (mediaList.isEmpty()) {
        for (uri in list) {
            Media.createFromUri(uri)?.let { mediaList.add(it) }
        }
    }
    
    return mediaList
}