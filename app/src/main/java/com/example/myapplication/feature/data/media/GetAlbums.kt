package com.example.myapplication.feature.data.media

import android.content.ContentResolver
import android.os.Build
import android.provider.MediaStore
import com.example.myapplication.feature.data.ContentQuery
import com.example.gallery.feature.domain.model.Album


fun ContentResolver.getAlbums(contentQuery: ContentQuery = ContentQuery.AlbumQuery()): List<Album> {
    val albums = HashMap<Long, Album>()

    query(contentQuery).use { cursor ->
        val idColumn = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.BUCKET_ID)
        val labelColumn = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.BUCKET_DISPLAY_NAME)
        val thumbnailPathColumn = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA)
        val thumbnailRelativePathColumn = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.RELATIVE_PATH)
        val thumbnailDateColumn = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATE_MODIFIED)

        while (cursor.moveToNext()) {
            try {
                val id = cursor.getLong(idColumn)
                val label: String? = cursor.getString(labelColumn) ?: Build.MODEL
                val thumbnailPath = cursor.getString(thumbnailPathColumn)
                val thumbnailRelativePath = cursor.getString(thumbnailRelativePathColumn)
                val thumbnailDate = cursor.getLong(thumbnailDateColumn)

                val currentAlbum = albums.getOrPut(id) {
                    Album(
                        id = id, label = label ?: Build.MODEL, pathToThumbnail = thumbnailPath,
                        relativePath = thumbnailRelativePath, timestamp = thumbnailDate, count = 0
                    )
                }

                currentAlbum.count++
                if (currentAlbum.timestamp <= thumbnailDate) {
                    currentAlbum.count = currentAlbum.count
                    currentAlbum.timestamp = thumbnailDate
                }

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        return albums.values.toList()
    }
}

