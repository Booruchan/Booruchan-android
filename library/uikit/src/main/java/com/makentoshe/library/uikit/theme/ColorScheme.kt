package com.makentoshe.library.uikit.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

val LightMaterialColorScheme = lightColorScheme(
    primary = Black, onPrimary = White,

    secondary = White, onSecondary = Black,

    background = White, onBackground = Black
)

val LightColorScheme = BooruchanColorScheme(
    title = "Light color scheme",
    accent = Black,
    onAccent = White,
    opaque = BlackA30,
    dimmed = BlackA60,
    background = White,
    foreground = Black,
    dashboard = BlueGray25,
    separator = Grey32,
    tag = BooruchanTagScheme(
        general = Black,
        artist = Red500,
        character = Green500,
        copyright = Purple500,
        metadata = Amber500,
    )
)


val DarkColorScheme = BooruchanColorScheme(
    title = "Dark color scheme",
    accent = White,
    onAccent = White,
    opaque = WhiteA30,
    dimmed = WhiteA60,
    background = Black,
    foreground = White,
    dashboard = Grey925,
    separator = Grey75,
    tag = BooruchanTagScheme(
        general = White,
        artist = Red500,
        character = Green500,
        copyright = Purple500,
        metadata = Amber500,
    )
)

val DarkMaterialColorScheme = darkColorScheme(
    primary = Black, onPrimary = White,

    secondary = White, onSecondary = Black,

    background = White, onBackground = Black
)


data class BooruchanColorScheme(
    internal val title: String,
    val accent: Color,
    val onAccent: Color,
    val opaque: Color,
    val dimmed: Color,
    val foreground: Color,
    val background: Color,
    val dashboard: Color,
    val separator: Color,
    val tag: BooruchanTagScheme
)

val BooruchanColorScheme.transparent: Color
    get() = Color.Transparent

val BooruchanColorScheme.error: Color
    get() = Color.Red

data class BooruchanTagScheme(
    val general: Color,
    val artist: Color,
    val character: Color,
    val copyright: Color,
    val metadata: Color,
)
