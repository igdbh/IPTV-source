package com.iptvsource.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.iptvsource.database.entities.EpgEntity
import com.iptvsource.database.viewmodel.EpgViewModel

@Composable
fun EpgScreen(channelId: Int, epgViewModel: EpgViewModel = viewModel(), onCatchUpClick: (String) -> Unit) {
    val epgList by epgViewModel.getEpgForChannel(channelId).collectAsState(initial = emptyList())

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("EPG - לוח שידורים", style = MaterialTheme.typography.h5)

        LazyColumn {
            items(epgList) { program ->
                EpgItem(program, onCatchUpClick)
            }
        }
    }
}

@Composable
fun EpgItem(program: EpgEntity, onCatchUpClick: (String) -> Unit) {
    Card(modifier = Modifier.fillMaxWidth().padding(8.dp).clickable {
        program.catchUpUrl?.let { onCatchUpClick(it) }
    }) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = program.title, style = MaterialTheme.typography.h6)
            Text(text = "⏰ ${program.startTime} - ${program.endTime}")
            if (program.catchUpUrl != null) {
                Text("🎬 זמין ב-Catch-Up!", color = MaterialTheme.colors.primary)
            } else {
                Text("❌ אין Catch-Up", color = MaterialTheme.colors.error)
            }
        }
    }
}
