package com.example.myapplication.feature.presentation.main.component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.TopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

@Composable
fun SimpleTopAppBar(
    title: String,
    backgroundColor: Color = MaterialTheme.colorScheme.surface,
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
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.onSurface,
            fontWeight = FontWeight.Bold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
        menuButton?.let { it() }
    }
}