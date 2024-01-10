package com.example.myapplication.feature.presentation.media

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.gallery.feature.presentation.media.MediaViewModel
import com.example.gallery.feature.presentation.media.SortOrder
import com.example.myapplication.R
import com.example.myapplication.core.model.MediaState
import com.example.myapplication.feature.presentation.main.component.SimpleTopAppBar
import com.example.myapplication.feature.presentation.media.component.MediaImage
import com.example.myapplication.feature.presentation.media.component.SortBar

@Composable
fun MediaScreen(
    navController: NavController,
    viewModel: MediaViewModel = hiltViewModel()
) {
    val screenState by viewModel.screenState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.surface)
    ) {
        SimpleTopAppBar(
            title = viewModel.album.label,
            navigationButton = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_arrow_back),
                        contentDescription = null,
                        tint = MaterialTheme.colors.onSurface,
                    )
                }
            },
        )

        SortBar(
            sortOrder = viewModel.sortOrder,
            onSortOrderChange = { viewModel.onSortOrderChange(it) },
        )

        when (viewModel.sortOrder) {
            SortOrder.Grid -> GridList(screenState)
            SortOrder.Linear -> LinearList(screenState)
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LinearList(state: MediaState) {
    LazyColumn(
        modifier = Modifier
            .background(MaterialTheme.colors.surface)
            .fillMaxSize(),
        contentPadding = PaddingValues(vertical = 20.dp, horizontal = 4.dp),
        verticalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        items(state.media, key = { "${it.id}" }) {
            val modifier = remember {
                Modifier.combinedClickable(onClick = {
                    // TODO: open fullscreen
                })
            }
            MediaImage(it, modifier = modifier)
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun GridList(state: MediaState) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        modifier = Modifier
            .background(MaterialTheme.colors.surface)
            .fillMaxSize(),
        contentPadding = PaddingValues(vertical = 20.dp, horizontal = 4.dp),
        verticalArrangement = Arrangement.spacedBy(2.dp),
        horizontalArrangement = Arrangement.spacedBy(2.dp),
    ) {
        items(state.media, key = { "${it.id}" }) {
            val modifier = remember {
                Modifier.combinedClickable(onClick = {
                    // TODO: open fullscreen
                })
            }
            MediaImage(it, modifier = modifier)
        }
    }
}