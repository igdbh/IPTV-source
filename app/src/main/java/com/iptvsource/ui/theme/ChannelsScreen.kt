package com.iptvsource.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.iptvsource.database.entities.ChannelEntity
import com.iptvsource.database.viewmodel.ChannelViewModel
import kotlinx.coroutines.launch

@Composable
fun ChannelsScreen(viewModel: ChannelViewModel) {
    val channels by viewModel.allChannels.collectAsState(initial = emptyList())
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Channels") },
                backgroundColor = Color.Blue,
                contentColor = Color.White
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                coroutineScope.launch {
                    viewModel.fetchLiveStreams("username", "password")
                }
            }) {
                Text("+", fontSize = 24.sp)
            }
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            items(channels) { channel ->
                ChannelItem(channel)
            }
        }
    }
}

@Composable
fun ChannelItem(channel: ChannelEntity) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Image(
            painter = rememberAsyncImagePainter(model = channel.logo),
            contentDescription = "Channel Logo",
            modifier = Modifier
                .size(64.dp)
                .padding(end = 8.dp),
            contentScale = ContentScale.Crop
        )

        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = channel.name,
                style = MaterialTheme.typography.h6,
                fontSize = 18.sp
            )
            Text(
                text = channel.category,
                style = MaterialTheme.typography.body2,
                fontSize = 14.sp,
                color = Color.Gray
            )
        }
    }
}
