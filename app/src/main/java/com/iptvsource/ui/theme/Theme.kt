package com.iptvsource.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// ✅ הגדרת צבעים ללא כפילויות
val Purple40 = Color(0xFF6200EE)
val Purple80 = Color(0xFF3700B3)
val PurpleGrey40 = Color(0xFFBB86FC)
val PurpleGrey80 = Color(0xFF6200EA)
val Pink40 = Color(0xFFFF4081)
val Pink80 = Color(0xFFFF80AB)
val Teal200 = Color(0xFF03DAC5)

@Composable
fun IPTVSourceTheme(
    isInDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val colorScheme = if (isInDarkTheme) {
        darkColorScheme(
            primary = Purple80,
            secondary = PurpleGrey80,
            tertiary = Pink80
        )
    } else {
        lightColorScheme(
            primary = Purple40,
            secondary = PurpleGrey40,
            tertiary = Pink40
        )
    }

    MaterialTheme(
        colorScheme = colorScheme,
        content = content
    )
}
