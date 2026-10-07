package com.example.mytos.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val MytosLightColorScheme = lightColorScheme(
    primary = MytosPurple,
    secondary = MytosYellow,
    tertiary = MytosGreen,

    background = MytosCream,
    surface = MytosWhite,

    onPrimary = MytosWhite,
    onSecondary = MytosText,
    onTertiary = MytosWhite,

    onBackground = MytosText,
    onSurface = MytosText
)

private val MytosDarkColorScheme = darkColorScheme(
    primary = MytosPurple,
    secondary = MytosYellow,
    tertiary = MytosGreen,

    background = MytosPurpleDeep,
    surface = MytosPurpleDark,

    onPrimary = MytosWhite,
    onSecondary = MytosText,
    onTertiary = MytosWhite,

    onBackground = MytosWhite,
    onSurface = MytosWhite
)

@Composable
fun MytosTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) {
        MytosDarkColorScheme
    } else {
        MytosLightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}