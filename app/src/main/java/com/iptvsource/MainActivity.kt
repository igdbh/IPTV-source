package com.iptvsource

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.rememberNavController
import com.iptvsource.database.data.AppDatabase
import com.iptvsource.database.repository.ChannelRepository
import com.iptvsource.database.repository.VodRepository
import com.iptvsource.database.repository.XtreamCodesRepository
import com.iptvsource.database.network.XtreamCodesApiService
import com.iptvsource.database.viewmodel.VodViewModel
import com.iptvsource.database.viewmodel.VodViewModelFactory
import com.iptvsource.database.viewmodel.ChannelViewModel
import com.iptvsource.database.viewmodel.ChannelViewModelFactory
import com.iptvsource.ui.navigation.NavigationGraph // ✅ תיקון הנתיב של ה-NavigationGraph

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val database = AppDatabase.getDatabase(this)

        val channelRepository = ChannelRepository(database.channelDao())
        val vodRepository = VodRepository(database.vodDao())

        val apiService = XtreamCodesApiService.create("http://your-xtream-url/") // ✅ הוספת baseUrl
        val xtreamCodesRepository = XtreamCodesRepository(
            apiService = apiService,
            channelRepository = channelRepository,
            vodRepository = vodRepository
        )

        val channelViewModel = ViewModelProvider(
            this,
            ChannelViewModelFactory(channelRepository, xtreamCodesRepository)
        )[ChannelViewModel::class.java]

        val vodViewModel = ViewModelProvider(
            this,
            VodViewModelFactory(VodRepository)
        )[VodViewModel::class.java]

        setContent {
            val navController = rememberNavController()
            NavigationGraph(
                navController,
                channelViewModel,
                vodViewModel
            )
        }
    }
}
