package com.linx.playAndroid.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.linx.common.model.ThemeType

/* Other default colors to override
background = Color.White,
surface = Color.White,
onPrimary = Color.White,
onSecondary = Color.Black,
onBackground = Color.Black,
onSurface = Color.Black,
*/

object CustomThemeManager {

    object Default {
        val darkColors = darkColors(
            primary = Default200,
            primaryVariant = Default700,
            secondary = DefaultSecondary,
            secondaryVariant = Color.White,
            background = Color.Black,
            surface = Color.White,
        )

        val lightColors = lightColors(
            primary = Default500,
            primaryVariant = Default700,
            secondary = DefaultSecondary,
            secondaryVariant = Color.Black,
            background = Color.White,
            surface = DefaultOnPrimary
        )
    }

    object Theme1 {
        val darkColors = darkColors(
            primary = Theme1_200,
            primaryVariant = Theme1_700,
            secondary = Theme1Secondary,
            secondaryVariant = Color.White,
            background = Color.Black,
            surface = Color.White,
        )

        val lightColors = lightColors(
            primary = Theme1_500,
            primaryVariant = Theme1_700,
            secondary = Theme1Secondary,
            secondaryVariant = Color.Black,
            background = Color.White,
            surface = Theme1OnPrimary
        )
    }

    object Theme2 {
        val darkColors = darkColors(
            primary = Theme2_200,
            primaryVariant = Theme2_700,
            secondary = Theme2Secondary,
            secondaryVariant = Color.White,
            background = Color.Black,
            surface = Color.White,
        )

        val lightColors = lightColors(
            primary = Theme2_500,
            primaryVariant = Theme2_700,
            secondary = Theme2Secondary,
            secondaryVariant = Color.Black,
            background = Color.White,
            surface = Theme2OnPrimary
        )
    }

    object Theme3 {
        val darkColors = darkColors(
            primary = Theme3_200,
            primaryVariant = Theme3_700,
            secondary = Theme3Secondary,
            secondaryVariant = Color.White,
            background = Color.Black,
            surface = Color.White,
        )

        val lightColors = lightColors(
            primary = Theme3_500,
            primaryVariant = Theme3_700,
            secondary = Theme3Secondary,
            secondaryVariant = Color.Black,
            background = Color.White,
            surface = Theme3OnPrimary
        )
    }

    object Theme4 {
        val darkColors = darkColors(
            primary = Theme4_200,
            primaryVariant = Theme4_700,
            secondary = Theme4Secondary,
            secondaryVariant = Color.White,
            background = Color.Black,
            surface = Color.White,
        )

        val lightColors = lightColors(
            primary = Theme4_500,
            primaryVariant = Theme4_700,
            secondary = Theme4Secondary,
            secondaryVariant = Color.Black,
            background = Color.White,
            surface = Theme4OnPrimary
        )
    }


    object Theme5 {
        val darkColors = darkColors(
            primary = Theme5_200,
            primaryVariant = Theme5_700,
            secondary = Theme5Secondary,
            secondaryVariant = Color.White,
            background = Color.Black,
            surface = Color.White,
        )

        val lightColors = lightColors(
            primary = Theme5_500,
            primaryVariant = Theme5_700,
            secondary = Theme5Secondary,
            secondaryVariant = Color.Black,
            background = Color.White,
            surface = Theme5OnPrimary
        )
    }

    @Composable
    fun WanAndroidTheme(
        type: ThemeType,
        darkTheme: Boolean = isSystemInDarkTheme(),
        content: @Composable () -> Unit
    ) {

        val wrappedColor = getWrappedColor(type)
        val colors = if (darkTheme) wrappedColor.darkColors else wrappedColor.lightColors

        MaterialTheme(
            colors = colors,
            typography = Typography,
            shapes = Shapes,
            content = content
        )

    }

    fun getWrappedColor(type: ThemeType): WrappedColor {
        val darkColors: Colors
        val lightColors: Colors

        when(type) {
            ThemeType.Default -> {
                darkColors = Default.darkColors
                lightColors = Default.lightColors
            }
            ThemeType.Theme1 -> {
                darkColors = Theme1.darkColors
                lightColors = Theme1.lightColors
            }
            ThemeType.Theme2 -> {
                darkColors = Theme2.darkColors
                lightColors = Theme2.lightColors
            }
            ThemeType.Theme3 -> {
                darkColors = Theme3.darkColors
                lightColors = Theme3.lightColors
            }
            ThemeType.Theme4 -> {
                darkColors = Theme4.darkColors
                lightColors = Theme4.lightColors
            }
            ThemeType.Theme5 -> {
                darkColors = Theme5.darkColors
                lightColors = Theme5.lightColors
            }
        }

        return WrappedColor(lightColors, darkColors)
    }

    /**
     * 获取主题样式颜色
     */
    @Composable
    fun getThemeColor(themeType: ThemeType): Colors {
        return when(themeType) {
            ThemeType.Default -> if (isSystemInDarkTheme()) Default.darkColors else Default.lightColors
            ThemeType.Theme1 -> if (isSystemInDarkTheme()) Theme1.darkColors else Theme1.lightColors
            ThemeType.Theme2 -> if (isSystemInDarkTheme()) Theme2.darkColors else Theme2.lightColors
            ThemeType.Theme3 -> if (isSystemInDarkTheme()) Theme3.darkColors else Theme3.lightColors
            ThemeType.Theme4 -> if (isSystemInDarkTheme()) Theme4.darkColors else Theme4.lightColors
            ThemeType.Theme5 -> if (isSystemInDarkTheme()) Theme5.darkColors else Theme5.lightColors
        }
    }

}

data class WrappedColor(val lightColors: Colors, val darkColors: Colors)

