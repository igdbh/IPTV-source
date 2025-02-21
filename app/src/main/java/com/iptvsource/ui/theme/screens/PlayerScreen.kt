package com.iptvsource.ui.screens

import android.net.Uri
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.LifecycleOwner
import androidx.compose.ui.viewinterop.AndroidView
import kotlinx.coroutines.launch
import androidx.compose.runtime.rememberCoroutineScope
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView

@Composable
fun PlayerScreen(url: String, lifecycleOwner: LifecycleOwner) {
    val coroutineScope = rememberCoroutineScope()

    val player = remember {
        ExoPlayer.Builder(lifecycleOwner as android.content.Context).build().apply {
            val mediaItem = MediaItem.fromUri(Uri.parse(url))
            setMediaItem(mediaItem)
            prepare()
            playWhenReady = true
        }
    }

    AndroidView(
        factory = { context ->
            PlayerView(context).apply {
                useController = true
                player = player
            }
        },
        modifier = Modifier.fillMaxSize()
    )
}
