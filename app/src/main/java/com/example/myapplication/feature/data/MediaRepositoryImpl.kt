package com.example.myapplication.feature.data

import android.content.ContentResolver
import android.content.ContentValues
import android.net.Uri
import android.provider.MediaStore
import com.example.gallery.feature.domain.model.Album
import com.example.gallery.feature.domain.model.Media
import com.example.myapplication.core.Resource
import com.example.myapplication.feature.data.media.findMedia
import com.example.myapplication.feature.data.media.getAlbums
import com.example.myapplication.feature.data.media.getMedia
import com.example.myapplication.feature.data.media.getMediaByUri
import com.example.myapplication.feature.data.media.getMediaListByUris
import com.example.myapplication.feature.domain.MediaRepository
import com.example.myapplication.feature.presentation.album.AllowedMedia
import com.example.myapplication.feature.presentation.album.AllowedMedia.BOTH
import com.example.myapplication.feature.presentation.album.AllowedMedia.PHOTOS
import com.example.myapplication.feature.presentation.album.AllowedMedia.VIDEOS

/** base use-case handle IO Thread */
class MediaRepositoryImpl(private val contentResolver: ContentResolver) : MediaRepository {

    override fun getMedia(): Resource<List<Media>> {
        return try {
            Resource.Success(data = contentResolver.getMedia())
        } catch (e: Exception) {
            Resource.Error(message = e.localizedMessage ?: "An error occurred")
        }
    }

    override fun getMediaByType(allowedMedia: AllowedMedia): Resource<List<Media>> {
        return try {
            val contentQuery = when (allowedMedia) {
                PHOTOS -> ContentQuery.PhotoQuery()
                VIDEOS -> ContentQuery.VideoQuery()
                BOTH -> ContentQuery.MediaQuery()
            }
            Resource.Success(data = contentResolver.getMedia(mediaQuery = contentQuery))
        } catch (e: Exception) {
            Resource.Error(message = e.localizedMessage ?: "An error occurred")
        }
    }

    override fun getAlbums(): Resource<List<Album>> {
        return try {
            val allPhotos = contentResolver.getMedia(ContentQuery.PhotoQuery())
            val allVideos = contentResolver.getMedia(ContentQuery.VideoQuery())
            val data = contentResolver.getAlbums().toMutableList()
            allPhotos.firstOrNull()?.let { album -> data.add(0, Album.allPhotos(album.path, album.relativePath, allPhotos.size.toLong())) }
            allVideos.firstOrNull()?.let { album -> data.add(1, Album.allVideos(album.path, album.relativePath, allVideos.size.toLong())) }
            Resource.Success(data = data.toList())
        } catch (e: Exception) {
            Resource.Error(message = e.localizedMessage ?: "An error occurred")
        }
    }
    override fun getMediaById(mediaId: Long): Media? {
        val contentQuery = ContentQuery.MediaQuery()
        return contentResolver.findMedia(contentQuery)
    }

    override fun getMediaByAlbumId(albumId: Long): Resource<List<Media>> {
        return try {
            val contentQuery = ContentQuery.MediaQuery().copy(
                selection = MediaStore.MediaColumns.BUCKET_ID + "= ?",
                selectionArgs = arrayOf(albumId.toString())
            )

            Resource.Success(data = contentResolver.getMedia(contentQuery))
        } catch (e: Exception) {
            Resource.Error(message = e.localizedMessage ?: "An error occurred")
        }
    }

    override fun getMediaByAlbumIdWithType(albumId: Long, allowedMedia: AllowedMedia): Resource<List<Media>> {
        return try {
            val mimeType = when (allowedMedia) {
                PHOTOS -> "image%"
                VIDEOS -> "video%"
                BOTH -> "%/%"
            }
            val contentQuery = ContentQuery.MediaQuery().copy(
                selection = MediaStore.MediaColumns.BUCKET_ID + "= ? and " + MediaStore.MediaColumns.MIME_TYPE + " like ?",
                selectionArgs = arrayOf(albumId.toString(), mimeType)
            )

            Resource.Success(data = contentResolver.getMedia(contentQuery))
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


    override fun getMediaByUri(uriAsString: String): Resource<List<Media>> {
        return try {
            val media = contentResolver.getMediaByUri(Uri.parse(uriAsString))
            if (media == null) {
                Resource.Error(message = "Media could not be opened")
            } else {
                val contentQuery = ContentQuery.MediaQuery().copy(
                    selection = MediaStore.MediaColumns.BUCKET_ID + "= ?",
                    selectionArgs = arrayOf(media.albumID.toString())
                )
                Resource.Success(data = contentResolver.getMedia(contentQuery).ifEmpty { listOf(media) })
            }
        } catch (e: Exception) {
            Resource.Error(message = e.localizedMessage ?: "An error occurred")
        }
    }


    override fun getMediaListByUris(listOfUris: List<Uri>, reviewMode: Boolean): Resource<List<Media>> {
        return try {
            var mediaList = contentResolver.getMediaListByUris(listOfUris)
            if (reviewMode) {
                val contentQuery = ContentQuery.MediaQuery().copy(
                    selection = MediaStore.MediaColumns.BUCKET_ID + "= ?",
                    selectionArgs = arrayOf(mediaList.first().albumID.toString())
                )
                mediaList = contentResolver.getMedia(contentQuery)
            }
            if (mediaList.isEmpty()) {
                Resource.Error(message = "Media could not be opened")
            } else {
                Resource.Success(data = mediaList)
            }
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