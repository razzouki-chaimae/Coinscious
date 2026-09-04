package com.chaimaerazzouki.designsystem

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val CoinsciousLightColorScheme = lightColorScheme(

    // Brand
    primary = ForestGreen,
    onPrimary = TextOnPrimary,

    primaryContainer = LightSage,
    onPrimaryContainer = DeepForestGreen,

    secondary = SageGreen,
    onSecondary = TextPrimary,

    secondaryContainer = VeryLightGreen,
    onSecondaryContainer = DeepForestGreen,

    tertiary = EducationTeal,
    onTertiary = Color.White,

    // Background
    background = WarmBackground,
    onBackground = TextPrimary,

    surface = SurfaceWhite,
    onSurface = TextPrimary,

    surfaceVariant = SurfaceSoft,
    onSurfaceVariant = TextSecondary,

    // Borders
    outline = BorderColor,
    outlineVariant = DividerColor,

    // Errors
    error = DestructiveRed,
    onError = Color.White,

    errorContainer = DestructiveBackground,
    onErrorContainer = DestructiveRed
)


private val CoinsciousDarkColorScheme = darkColorScheme(

    primary = SageGreen,
    onPrimary = DeepForestGreen,

    primaryContainer = ForestGreen,
    onPrimaryContainer = Color.White,

    secondary = SageGreen,
    onSecondary = DeepForestGreen,

    secondaryContainer = Color(0xFF294532),
    onSecondaryContainer = Color(0xFFDCEED8),

    tertiary = Color(0xFF7BC1BD),
    onTertiary = Color(0xFF003735),

    background = Color(0xFF101712),
    onBackground = Color(0xFFE8EEE7),

    surface = Color(0xFF151D17),
    onSurface = Color(0xFFE8EEE7),

    surfaceVariant = Color(0xFF273229),
    onSurfaceVariant = Color(0xFFC1CCC1),

    outline = Color(0xFF89958A),

    error = Color(0xFFFF8A7A),
    onError = Color(0xFF5A0B05),

    errorContainer = Color(0xFF7A211A),
    onErrorContainer = Color(0xFFFFDAD5)
)


@Composable
fun CoinsciousTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Important: disable dynamic colors so Coinscious
    // always keeps its own visual identity.
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {

    val colorScheme = when {

        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current

            if (darkTheme) {
                dynamicDarkColorScheme(context)
            } else {
                dynamicLightColorScheme(context)
            }
        }

        darkTheme -> CoinsciousDarkColorScheme

        else -> CoinsciousLightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = CoinsciousTypography,
        //shapes = CoinsciousShapes,
        content = content
    )
}