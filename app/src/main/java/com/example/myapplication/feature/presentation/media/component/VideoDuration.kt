package com.example.gallery.feature.presentation.media.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.gallery.feature.domain.model.Media
import com.example.myapplication.core.component.advancedShadow
import com.example.myapplication.core.formatDate

@Composable
fun VideoDuration(modifier: Modifier = Modifier, media: Media) {
    Row(
        modifier = modifier
            .padding(all = 8.dp)
            .advancedShadow(
                cornersRadius = 8.dp,
                shadowBlurRadius = 6.dp,
                alpha = 0.3f
            ),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier,
            text = media.duration.formatDate(),
            style = MaterialTheme.typography.labelSmall,
            color = Color.White
        )
        Spacer(modifier = Modifier.size(2.dp))
    }
}