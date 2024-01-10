package com.example.myapplication.feature.presentation.media.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.decode.VideoFrameDecoder
import com.example.gallery.feature.domain.model.Media
import com.example.gallery.feature.presentation.media.component.VideoDuration
import com.example.myapplication.R
import com.example.myapplication.core.Constants
import com.example.myapplication.core.component.forwardingPainter

@Composable
fun MediaImage(
    media: Media,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .background(MaterialTheme.colorScheme.surface)
            .fillMaxSize()
    ) {

        AsyncImage(
            model = media.uri,
            imageLoader = ImageLoader.Builder(LocalContext.current).components { add(VideoFrameDecoder.Factory()) }.build(),
            contentDescription = media.label,
            contentScale = ContentScale.Crop,
            modifier = modifier
                .aspectRatio(1f)
                .clip(RoundedCornerShape(2.dp)),
            error = forwardingPainter(
                painter = painterResource(R.drawable.ic_broken_image),
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSurface),
            )
        )

        AnimatedVisibility(
            visible = media.duration != null,
            enter = Constants.Animation.enterAnimation,
            exit = Constants.Animation.exitAnimation,
            modifier = Modifier.align(Alignment.TopEnd)
        ) {
            VideoDuration(
                modifier = Modifier,
                media = media
            )
        }
    }
}