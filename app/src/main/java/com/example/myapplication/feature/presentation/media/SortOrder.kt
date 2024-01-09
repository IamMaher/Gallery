package com.example.gallery.feature.presentation.media

enum class SortOrder {
    Grid, Linear;
    companion object {
        fun fromString(str: String): SortOrder {
            return when (str) {
                Grid.toString() -> Grid
                Linear.toString() -> Linear
                else -> Grid
            }
        }
    }
}