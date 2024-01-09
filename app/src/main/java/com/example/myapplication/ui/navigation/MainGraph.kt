package com.example.myapplication.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.myapplication.feature.presentation.album.AlbumScreen

@Composable
fun MainGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(navController = navController, startDestination = Screen.AlbumScreen.route, modifier = modifier) {
        composable(Screen.AlbumScreen.route) { AlbumScreen(navController = navController) }

    }
}