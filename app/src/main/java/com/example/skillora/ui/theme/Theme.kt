package com.example.skillora.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = ElectricBlue,
    onPrimary = Color.White,
    primaryContainer = DeepBlue,
    onPrimaryContainer = CyanGlow,
    secondary = CyanGlow,
    onSecondary = MidnightNavy,
    background = MidnightNavy,
    onBackground = Color.White,
    surface = SurfaceDark,
    onSurface = Color.White,
    surfaceVariant = CardDark,
    onSurfaceVariant = Color(0xFFCBD5E1),
    outline = BorderDark
)

private val LightColorScheme = lightColorScheme(
    primary = ElectricBlue,
    onPrimary = Color.White,
    primaryContainer = LightBlueSubtle,
    onPrimaryContainer = DeepBlue,
    secondary = DeepBlue,
    onSecondary = Color.White,
    background = SlateLightBackground,
    onBackground = TextDarkPrimary,
    surface = CardLight,
    onSurface = TextDarkPrimary,
    surfaceVariant = Color(0xFFF1F5F9),
    onSurfaceVariant = TextDarkSecondary,
    outline = BorderLight
)

@Composable
fun SkilloraTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as? Activity)?.window
            if (window != null) {
                window.statusBarColor = colorScheme.background.toArgb()
                WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
            }
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
