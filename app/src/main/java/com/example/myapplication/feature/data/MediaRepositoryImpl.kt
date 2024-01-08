package com.example.myapplication.feature.data

import android.content.ContentResolver
import android.content.ContentValues
import android.provider.MediaStore
import com.example.gallery.feature.domain.model.Album
import com.example.myapplication.core.Resource
import com.example.myapplication.feature.data.media.getAlbums
import com.example.myapplication.feature.domain.MediaRepository
import com.example.myapplication.feature.presentation.album.AllowedMedia
import com.example.myapplication.feature.presentation.album.AllowedMedia.BOTH
import com.example.myapplication.feature.presentation.album.AllowedMedia.PHOTOS
import com.example.myapplication.feature.presentation.album.AllowedMedia.VIDEOS

/** base use-case handle IO Thread */
class MediaRepositoryImpl(private val contentResolver: ContentResolver) : MediaRepository {

    override fun getAlbums(): Resource<List<Album>> {
        return try {
            Resource.Success(data = contentResolver.getAlbums())
        } catch (e: Exception) {
            Resource.Error(message = e.localizedMessage ?: "An error occurred")
        }
    }


    override fun getAlbumsWithType(allowedMedia: AllowedMedia): Resource<List<Album>> {
        return try {
            val mimeType = when (allowedMedia) {
                PHOTOS -> "image%"
                VIDEOS -> "video%"
                BOTH -> "%/%"
            }
            val contentQuery = ContentQuery.AlbumQuery().copy(
                selection = MediaStore.MediaColumns.MIME_TYPE + " like ?",
                selectionArgs = arrayOf(mimeType)
            )

            Resource.Success(data = contentResolver.getAlbums(contentQuery))
        } catch (e: Exception) {
            Resource.Error(message = e.localizedMessage ?: "An error occurred")
        }
    }


    companion object {
        private fun displayName(newName: String) = ContentValues().apply {
            put(MediaStore.MediaColumns.DISPLAY_NAME, newName)
        }

        private fun relativePath(newPath: String) = ContentValues().apply {
            put(MediaStore.MediaColumns.RELATIVE_PATH, newPath)
        }
    }
}