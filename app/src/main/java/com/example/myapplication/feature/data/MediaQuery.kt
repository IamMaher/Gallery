package com.example.gallery.feature.data

import android.provider.MediaStore

sealed class ContentQuery(
    var projection: Array<String>,
    var selection: String? = null,
    var selectionArgs: Array<String>? = null,
    var sortOrder: String? = null,
) {
    class MediaQuery(bucketId: String? = null) : ContentQuery(
        projection = arrayOf(
            MediaStore.MediaColumns._ID,
            MediaStore.MediaColumns.DATA,
            MediaStore.MediaColumns.RELATIVE_PATH,
            MediaStore.MediaColumns.DISPLAY_NAME,
            MediaStore.MediaColumns.BUCKET_ID,
            MediaStore.MediaColumns.DATE_MODIFIED,
            MediaStore.MediaColumns.DATE_TAKEN,
            MediaStore.MediaColumns.BUCKET_DISPLAY_NAME,
            MediaStore.MediaColumns.DURATION,
            MediaStore.MediaColumns.MIME_TYPE,
            MediaStore.MediaColumns.ORIENTATION
        ),
        selection = bucketId?.let { "${MediaStore.Images.Media.BUCKET_ID} = ?" },
        selectionArgs = bucketId?.let { id -> arrayOf(id) },
        sortOrder = "${MediaStore.MediaColumns.DATE_TAKEN} DESC"
    )


    class PhotoQuery(bucketId: String? = null) : ContentQuery(
        projection = arrayOf(
            MediaStore.MediaColumns._ID,
            MediaStore.MediaColumns.DATA,
            MediaStore.MediaColumns.RELATIVE_PATH,
            MediaStore.MediaColumns.DISPLAY_NAME,
            MediaStore.MediaColumns.BUCKET_ID,
            MediaStore.MediaColumns.DATE_MODIFIED,
            MediaStore.MediaColumns.DATE_TAKEN,
            MediaStore.MediaColumns.BUCKET_DISPLAY_NAME,
            MediaStore.MediaColumns.DURATION,
            MediaStore.MediaColumns.MIME_TYPE,
            MediaStore.MediaColumns.ORIENTATION
        ),
        selection = bucketId?.let { MediaStore.MediaColumns.BUCKET_ID + "= ? and " + MediaStore.MediaColumns.MIME_TYPE + " like ?"
        } ?: "${MediaStore.MediaColumns.MIME_TYPE} LIKE ?",
        selectionArgs = bucketId?.let { id -> arrayOf("image%") + arrayOf(id) } ?: arrayOf("image%"),
        sortOrder = "${MediaStore.MediaColumns.DATE_TAKEN} DESC"
    )

    class VideoQuery(bucketId: String? = null) : ContentQuery(
        projection = arrayOf(
            MediaStore.MediaColumns._ID,
            MediaStore.MediaColumns.DATA,
            MediaStore.MediaColumns.RELATIVE_PATH,
            MediaStore.MediaColumns.DISPLAY_NAME,
            MediaStore.MediaColumns.BUCKET_ID,
            MediaStore.MediaColumns.DATE_MODIFIED,
            MediaStore.MediaColumns.DATE_TAKEN,
            MediaStore.MediaColumns.BUCKET_DISPLAY_NAME,
            MediaStore.MediaColumns.DURATION,
            MediaStore.MediaColumns.MIME_TYPE,
            MediaStore.MediaColumns.ORIENTATION
        ),
        selection = bucketId?.let {
            "${MediaStore.MediaColumns.MIME_TYPE} LIKE ? AND ${MediaStore.Images.Media.BUCKET_ID} = ?"
        } ?: "${MediaStore.MediaColumns.MIME_TYPE} LIKE ?",
        selectionArgs = bucketId?.let { id -> arrayOf("video%") + arrayOf(id) } ?: arrayOf("video%"),
        sortOrder = "${MediaStore.MediaColumns.DATE_TAKEN} DESC"
    )

    class AlbumQuery : ContentQuery(
        projection = arrayOf(
            MediaStore.MediaColumns.BUCKET_ID,
            MediaStore.MediaColumns.BUCKET_DISPLAY_NAME,
            MediaStore.MediaColumns.DISPLAY_NAME,
            MediaStore.MediaColumns.DATA,
            MediaStore.MediaColumns.RELATIVE_PATH,
            MediaStore.MediaColumns._ID,
            MediaStore.MediaColumns.DATE_MODIFIED,
            MediaStore.MediaColumns.DATE_TAKEN
        ),
        selection = null,
        selectionArgs = null,
        sortOrder = null,
    )

    fun copy(
        projection: Array<String> = this.projection,
        selection: String? = this.selection,
        selectionArgs: Array<String>? = this.selectionArgs,
        sortOrder: String? = this.sortOrder,
    ): ContentQuery {
        this.projection = projection
        this.selection = selection
        this.selectionArgs = selectionArgs
        this.sortOrder = sortOrder
        return this
    }
}