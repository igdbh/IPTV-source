package com.iptvsource

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.rememberNavController
import com.iptvsource.database.data.AppDatabase
import com.iptvsource.database.network.XtreamCodesApiService
import com.iptvsource.database.repository.ChannelRepository
import com.iptvsource.database.repository.XtreamCodesRepository
import com.iptvsource.database.viewmodel.ChannelViewModel
import com.iptvsource.database.viewmodel.ChannelViewModelFactory
import com.iptvsource.ui.theme.IPTVSourceTheme
import com.iptvsource.ui.theme.NavigationGraph

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val database = AppDatabase.getDatabase(this)
        val channelRepository = ChannelRepository(database.channelDao())
        val apiService = XtreamCodesApiService.create()
        val xtreamRepository = XtreamCodesRepository(apiService, channelRepository)

        val viewModelFactory = ChannelViewModelFactory(channelRepository, xtreamRepository)
        val channelViewModel = ViewModelProvider(this, viewModelFactory)[ChannelViewModel::class.java]

        setContent {
            val navController = rememberNavController()

            IPTVSourceTheme {
                NavigationGraph(navController, channelViewModel)
            }
        }
    }
}
