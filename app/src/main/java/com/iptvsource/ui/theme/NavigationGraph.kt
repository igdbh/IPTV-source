package com.iptvsource.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.iptvsource.ui.screens.EpgScreen
import com.iptvsource.ui.screens.PlayerScreen

@Composable
fun NavigationGraph(
    navController: NavHostController,
    channelViewModel: com.iptvsource.database.viewmodel.ChannelViewModel,
    vodViewModel: com.iptvsource.database.viewmodel.VodViewModel
) {
    NavHost(navController, startDestination = "epg/{channelId}") {
        composable("epg/{channelId}") { backStackEntry ->
            val channelId = backStackEntry.arguments?.getString("channelId")?.toInt() ?: 0
            EpgScreen(channelId, onCatchUpClick = { url ->
                navController.navigate("player/$url")
            })
        }

        composable("player/{url}") { backStackEntry ->
            val url = backStackEntry.arguments?.getString("url") ?: ""
            PlayerScreen(url)
        }
    }
}
