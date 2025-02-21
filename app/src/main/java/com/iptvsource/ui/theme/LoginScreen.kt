package com.iptvsource.ui.theme.screens

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.iptvsource.database.repository.ChannelRepository
import com.iptvsource.parser.M3UParser
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import androidx.navigation.NavHostController

@Composable
fun LoginScreen(navController: NavHostController, channelRepository: ChannelRepository) { // ✅ הוספנו את הפרמטר
    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("IPTVPrefs", Context.MODE_PRIVATE)

    var url by remember { mutableStateOf(sharedPreferences.getString("url", "") ?: "") }
    var username by remember { mutableStateOf(sharedPreferences.getString("username", "") ?: "") }
    var password by remember { mutableStateOf(sharedPreferences.getString("password", "") ?: "") }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp)
    ) {
        Text("Login", fontSize = 24.sp)

        OutlinedTextField(
            value = url,
            onValueChange = { url = it },
            label = { Text("M3U / Xtream URL") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Username (Xtream Only)") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password (Xtream Only)") },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                sharedPreferences.edit().apply {
                    putString("url", url)
                    putString("username", username)
                    putString("password", password)
                    apply()
                }

                // טוען את ה-M3U ושומר במסד הנתונים
                CoroutineScope(Dispatchers.IO).launch {
                    val channels = M3UParser.parseM3UFromUrl(url)
                    channelRepository.insertChannels(channels) // ✅ עכשיו זה יעבוד כי הוא קיים בפרמטרים
                }

                navController.navigate("channels")
            },
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text("Login & Load Channels")
        }
    }
}
