package com.iptvsource.ui.theme.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.iptvsource.database.viewmodel.ChannelViewModel
import com.iptvsource.database.viewmodel.VodViewModel

@Composable
fun ManageCategoriesScreen(
    channelViewModel: ChannelViewModel = viewModel(),
    vodViewModel: VodViewModel = viewModel()
) {
    val channels by channelViewModel.visibleChannels.collectAsState(initial = emptyList())
    val vods by vodViewModel.getVisibleVods().collectAsState(initial = emptyList())

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Manage Categories", style = MaterialTheme.typography.h5)

        channels.forEach { channel ->
            Row {
                Text(channel.name)
                Checkbox(
                    checked = !channel.isHidden,
                    onCheckedChange = { isChecked ->
                        channelViewModel.hideChannel(channel.id, !isChecked)
                    }
                )
            }
        }

        vods.forEach { vod ->
            Row {
                Text(vod.title)
                Checkbox(
                    checked = !vod.isHidden,
                    onCheckedChange = { isChecked ->
                        vodViewModel.hideVod(vod.id, !isChecked)
                    }
                )
            }
        }
    }
}
