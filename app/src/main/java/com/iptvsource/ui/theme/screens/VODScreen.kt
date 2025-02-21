package com.iptvsource.ui.theme.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.iptvsource.database.entities.VodEntity
import com.iptvsource.database.viewmodel.VodViewModel

@Composable
fun VODScreen(navController: NavController, viewModel: VodViewModel, username: String, password: String) {
    val vodList by viewModel.allVod.collectAsState(initial = emptyList())

    LaunchedEffect(Unit) {
        viewModel.fetchVod(username, password)  // ⬅️ עכשיו זה יעבוד בלי בעיות
    }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp)
    ) {
        Text("VOD", fontSize = 24.sp, modifier = Modifier.padding(bottom = 16.dp))

        LazyColumn {
            items(vodList) { vod ->
                VODItem(vod)
            }
        }
    }
}

@Composable
fun VODItem(vod: VodEntity) {
    Column(modifier = Modifier.padding(8.dp)) {
        Text(text = vod.name, fontSize = 18.sp, fontWeight = androidx.compose.ui.text.font.FontWeight.Bold)
        Text(text = "Category: ${vod.category}", fontSize = 14.sp)
    }
}
