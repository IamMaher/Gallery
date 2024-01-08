package com.example.gallery.feature.domain.model

import android.net.Uri
import android.os.Parcelable
import android.webkit.MimeTypeMap
import androidx.compose.runtime.Immutable
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize
import java.io.File
import java.util.Random

@Immutable
@Parcelize
data class Media(
    val id: Long = 0,
    val label: String,
    val uri: Uri,
    val path: String,
    val relativePath: String,
    val albumID: Long,
    val albumLabel: String,
    val timestamp: Long,
    val expiryTimestamp: Long? = null,
    val takenTimestamp: Long? = null,
    val mimeType: String,
    val orientation: Int,
    val duration: String? = null,
) : Parcelable {

    val isVideo: Boolean get() = mimeType.startsWith("video/") && duration != null
    val isImage: Boolean get() = mimeType.startsWith("image/")

    override fun toString(): String {
        return "$id, $path, $mimeType"
    }

    fun readUriOnly(): Boolean = albumID == -99L && albumLabel == ""

    @IgnoredOnParcel
    val isRaw: Boolean = mimeType.isNotBlank() && (mimeType.startsWith("image/x-") || mimeType.startsWith("image/vnd."))

    @IgnoredOnParcel
    val fileExtension: String = label.substringAfterLast(".").removePrefix(".")

    @IgnoredOnParcel
    val volume: String = path.substringBeforeLast("/").removeSuffix(relativePath.removeSuffix("/"))

    companion object {
        fun createFromUri(uri: Uri): Media? {
            if (uri.path == null) return null
            val extension = uri.toString().substringAfterLast(".")
            val mimeType = MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension).toString()
            var timestamp = 0L
            uri.path?.let { File(it) }?.let {
                timestamp = try {
                    it.lastModified()
                } catch (_: Exception) {
                    0L
                }
            }

            return Media(
                id = Random(System.currentTimeMillis()).nextLong(),
                label = uri.toString().substringAfterLast("/"),
                uri = uri,
                path = uri.path.toString(),
                relativePath = uri.path.toString().substringBeforeLast("/"),
                albumID = -99L,
                albumLabel = "",
                timestamp = timestamp,
                mimeType = mimeType,
                orientation = 0
            )
        }
    }
}
