package com.iptvsource.ui.theme

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.iptvsource.database.viewmodel.ChannelViewModel
import com.iptvsource.ui.theme.screens.ChannelsScreen
import com.iptvsource.ui.theme.screens.LoginScreen

@Composable
fun NavigationGraph(navController: NavHostController, channelViewModel: ChannelViewModel) {
    NavHost(navController, startDestination = "login") {
        composable("login") { LoginScreen(navController) }
        composable("channels") { ChannelsScreen(viewModel = channelViewModel) }
    }
}
