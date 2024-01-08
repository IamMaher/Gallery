package com.example.myapplication.feature.presentation.main

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.runtime.LaunchedEffect
import androidx.core.app.ActivityCompat
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.MultiplePermissionsState
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    val viewModel: MainActivityViewModel by viewModels()

    private val preTiramisuPermissions = listOf(
        Manifest.permission.READ_EXTERNAL_STORAGE,
    )

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    private val tiramisuPermissions = listOf(
        Manifest.permission.READ_MEDIA_IMAGES,
        Manifest.permission.READ_MEDIA_VIDEO
    )

    @OptIn(ExperimentalPermissionsApi::class)
    var storagePermissionState: MultiplePermissionsState? = null

    @OptIn(ExperimentalPermissionsApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                storagePermissionState = rememberMultiplePermissionsState(
                    permissions = if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU)
                        preTiramisuPermissions else tiramisuPermissions,
                    onPermissionsResult = {
                        it.forEach { (permission, granted) ->
                            if (!granted) {
                                if (!ActivityCompat.shouldShowRequestPermissionRationale(this, permission)) {
                                    val settingsIntent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                                    val uri = Uri.fromParts("package", packageName, null)
                                    settingsIntent.data = uri
                                    startActivity(settingsIntent)
                                } else {
                                    storagePermissionState?.launchMultiplePermissionRequest()
                                    Toast.makeText(this, "Permissions not granted", Toast.LENGTH_SHORT).show()
                                }
                            }
                        }
                    }
                )

                if (!storagePermissionState!!.allPermissionsGranted) {
                    // Request permissions
                    LaunchedEffect(true) {
                        storagePermissionState?.launchMultiplePermissionRequest()
                    }
                } else {
                    // Screen content
                    // TODO: navigate to next screen
                }
            }
        }
    }
}