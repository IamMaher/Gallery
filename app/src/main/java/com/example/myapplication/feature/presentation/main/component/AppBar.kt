package com.example.myapplication.feature.presentation.main.component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

@Composable
fun SimpleTopAppBar(
    title: String,
    backgroundColor: Color = MaterialTheme.colors.surface,
    navigationButton: @Composable (() -> Unit)? = null,
    menuButton: @Composable (() -> Unit)? = null,
) {
    TopAppBar(
        backgroundColor = backgroundColor,
        elevation = 8.dp,
    ) {
        navigationButton?.let { it() }
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            text = title,
            modifier = Modifier.weight(1f),
            style = MaterialTheme.typography.h6,
            color = MaterialTheme.colors.onSurface,
            fontWeight = FontWeight.Bold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
        menuButton?.let { it() }
    }
}