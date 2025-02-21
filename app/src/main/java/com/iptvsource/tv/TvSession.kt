package com.iptvsource.tv

import android.media.tv.TvInputService
import android.net.Uri
import android.util.Log
import android.view.Surface

class IptvTvSession(service: TvInputService) : TvInputService.Session(service) {

    override fun onRelease() {
        Log.d("IptvTvSession", "Session released")
    }

    override fun onSetSurface(surface: Surface?): Boolean {
        Log.d("IptvTvSession", "Surface set: $surface")
        return true
    }

    override fun onSetStreamVolume(volume: Float) {
        Log.d("IptvTvSession", "Stream volume set: $volume")
    }

    override fun onSetCaptionEnabled(enabled: Boolean) {
        Log.d("IptvTvSession", "Captions enabled: $enabled")
    }

    override fun onTune(channelUri: Uri?): Boolean {
        Log.d("IptvTvSession", "Tuning to channel: $channelUri")
        return true
    }
}
