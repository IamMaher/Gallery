package com.example.myapplication.feature.data.media

import android.content.ContentResolver
import com.example.gallery.feature.domain.model.Media
import com.example.myapplication.feature.data.ContentQuery

fun ContentResolver.getMedia(mediaQuery: ContentQuery = ContentQuery.MediaQuery()): List<Media> {
    val media = ArrayList<Media>()
    with(query(mediaQuery)) {
        moveToFirst()
        while (!isAfterLast) {
            try {
                media.add(getMediaFromCursor())
            } catch (e: Exception) {
                e.printStackTrace()
            }
            moveToNext()
        }
        close()

        return media
    }
}

fun ContentResolver.findMedia(mediaQuery: ContentQuery): Media? {
    val mediaList = getMedia(mediaQuery)
    return if (mediaList.isEmpty()) null else mediaList.first()
}