package com.example.myapplication.feature.presentation.album.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.decode.VideoFrameDecoder
import com.example.gallery.feature.domain.model.Album
import com.example.myapplication.R
import com.example.myapplication.core.component.forwardingPainter
import java.io.File


@Composable
fun AlbumItem(
    album: Album,
    thumbnail: String?,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = modifier
                .aspectRatio(1f)
                .clip(RoundedCornerShape(15))
        ) {
            AsyncImage(
                model = File(thumbnail?:""),
                imageLoader = ImageLoader.Builder(LocalContext.current).components { add(VideoFrameDecoder.Factory()) }.build(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = modifier.fillMaxWidth(),
                error = forwardingPainter(
                    painter = painterResource(R.drawable.ic_broken_image),
                    colorFilter = ColorFilter.tint(MaterialTheme.colors.onSurface),
                )
            )
            Text(
                text = album.count.toString(),
                style = MaterialTheme.typography.h6.copy(fontSize = 12.sp),
                color = MaterialTheme.colors.onPrimary,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(16.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .background(MaterialTheme.colors.onSurface.copy(alpha = 0.5f))
                    .widthIn(min = 16.dp)
                    .align(Alignment.BottomEnd)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = album.label,
            style = MaterialTheme.typography.h6.copy(fontSize = 18.sp),
            color = MaterialTheme.colors.onSurface,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
    }
}