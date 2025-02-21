package com.iptvsource.parser

import android.util.Log
import com.iptvsource.database.entities.ChannelEntity
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

object M3UParser {

    fun parseM3UFromUrl(m3uUrl: String): List<ChannelEntity> {
        return try {
            val url = URL(m3uUrl)
            val connection = url.openConnection() as HttpURLConnection
            connection.requestMethod = "GET"
            connection.connect()

            val inputStream = connection.inputStream
            parseM3U(inputStream)
        } catch (e: Exception) {
            Log.e("M3UParser", "❌ שגיאה בטעינת M3U: ${e.message}")
            emptyList()
        }
    }

    private fun parseM3U(inputStream: InputStream): List<ChannelEntity> {
        val channels = mutableListOf<ChannelEntity>()
        val reader = BufferedReader(InputStreamReader(inputStream))
        var line: String?

        var name: String? = null
        var logo: String? = null
        var url: String? = null
        var category: String = "Uncategorized"

        while (reader.readLine().also { line = it } != null) {
            val trimmedLine = line!!.trim()

            if (trimmedLine.startsWith("#EXTINF")) {
                val parts = trimmedLine.split(",")
                if (parts.size > 1) {
                    name = parts[1]
                }

                val attributes = trimmedLine.split(" ")
                for (attr in attributes) {
                    when {
                        attr.startsWith("tvg-logo=") -> logo = attr.substringAfter("tvg-logo=").replace("\"", "")
                        attr.startsWith("group-title=") -> category = attr.substringAfter("group-title=").replace("\"", "")
                    }
                }
            } else if (trimmedLine.startsWith("http")) {
                url = trimmedLine

                if (!url.isNullOrBlank() && !name.isNullOrBlank()) {
                    channels.add(
                        ChannelEntity(
                            name = name!!,
                            logo = logo ?: "",
                            category = category,
                            streamUrl = url!!
                        )
                    )
                }
            }
        }

        Log.d("M3UParser", "✅ נמצאו ${channels.size} ערוצים")
        return channels
    }
}
