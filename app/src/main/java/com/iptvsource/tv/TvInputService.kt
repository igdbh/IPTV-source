package com.iptvsource.tv

import android.media.tv.TvInputService
import android.util.Log

class IptvTvInputService : TvInputService() {

    override fun onCreateSession(inputId: String): Session {
        Log.d("IptvTvInputService", "Session created for inputId: $inputId")
        return IptvTvSession(this)
    }

    override fun onCreate() {
        super.onCreate()
        Log.d("IptvTvInputService", "TvInputService started")
    }
}
