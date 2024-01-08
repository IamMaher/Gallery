package com.example.myapplication.feature.data.media

import android.content.ContentResolver
import android.content.ContentUris
import android.database.Cursor
import android.database.MergeCursor
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import com.example.myapplication.feature.data.ContentQuery
import com.example.gallery.feature.domain.model.Media

fun contentUriVideo(): Uri = if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) MediaStore.Video.Media.EXTERNAL_CONTENT_URI else
    MediaStore.Video.Media.getContentUri(MediaStore.VOLUME_EXTERNAL)

fun contentUriImage(): Uri = if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) MediaStore.Images.Media.EXTERNAL_CONTENT_URI else
    MediaStore.Images.Media.getContentUri(MediaStore.VOLUME_EXTERNAL)

fun ContentResolver.query(mediaQuery: ContentQuery): Cursor {
    return MergeCursor(
        arrayOf(
            query(
                contentUriImage(),
                mediaQuery.projection,
                mediaQuery.selection,
                mediaQuery.selectionArgs,
                mediaQuery.sortOrder
            ),
            query(
                contentUriVideo(),
                mediaQuery.projection,
                mediaQuery.selection,
                mediaQuery.selectionArgs,
                mediaQuery.sortOrder
            )
        )
    )
}


@Throws(Exception::class)
fun Cursor.getMediaFromCursor(): Media {
    val id: Long = getLong(getColumnIndexOrThrow(MediaStore.MediaColumns._ID))
    val path: String = getString(getColumnIndexOrThrow(MediaStore.MediaColumns.DATA))
    val relativePath: String = getString(getColumnIndexOrThrow(MediaStore.MediaColumns.RELATIVE_PATH))
    val title: String = getString(getColumnIndexOrThrow(MediaStore.MediaColumns.DISPLAY_NAME))
    val albumID: Long = getLong(getColumnIndexOrThrow(MediaStore.MediaColumns.BUCKET_ID))

    val albumLabel: String = try {
        getString(getColumnIndexOrThrow(MediaStore.MediaColumns.BUCKET_DISPLAY_NAME))
    } catch (e: Exception) {
        Build.MODEL
    }
    val takenTimestamp: Long? = try {
        getLong(getColumnIndexOrThrow(MediaStore.MediaColumns.DATE_TAKEN))
    } catch (_: Exception) {
        null
    }
    val modifiedTimestamp: Long = getLong(getColumnIndexOrThrow(MediaStore.MediaColumns.DATE_MODIFIED))
    val duration: String? = try {
        getString(getColumnIndexOrThrow(MediaStore.MediaColumns.DURATION))
    } catch (_: Exception) {
        null
    }
    val orientation: Int = getInt(getColumnIndexOrThrow(MediaStore.MediaColumns.ORIENTATION))
    val mimeType: String = getString(getColumnIndexOrThrow(MediaStore.MediaColumns.MIME_TYPE))
    val expiryTimestamp: Long? = try {
        getLong(getColumnIndexOrThrow(MediaStore.MediaColumns.DATE_EXPIRES))
    } catch (_: Exception) {
        null
    }

    val contentUri = if (mimeType.contains("image")) contentUriImage()
    else contentUriVideo()

    val uri = ContentUris.withAppendedId(contentUri, id)

    return Media(
        id = id,
        label = title,
        uri = uri,
        path = path,
        relativePath = relativePath,
        albumID = albumID,
        albumLabel = albumLabel,
        timestamp = modifiedTimestamp,
        takenTimestamp = takenTimestamp,
        expiryTimestamp = expiryTimestamp,
        duration = duration,
        orientation = orientation,
        mimeType = mimeType
    )
}