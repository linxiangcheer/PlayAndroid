package com.linx.playAndroid

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.linx.common.baseData.Nav
import com.linx.playAndroid.composable.*

/**
 * 内容 导航
 */
@Composable
fun NavigationHost(navController: NavHostController, paddingValues: PaddingValues) {
    NavHost(
        navController,
        startDestination = Nav.BottomNavScreen.HomeScreen.route,
        Modifier.padding(paddingValues),
        builder = {
            composable(Nav.BottomNavScreen.HomeScreen.route) { HomeCompose(navController) }
            composable(Nav.BottomNavScreen.ProjectScreen.route) { ProjectCompose(navController) }
            composable(Nav.BottomNavScreen.SquareScreen.route) { SquareCompose(navController) }
            composable(Nav.BottomNavScreen.PublicNumScreen.route) { PublicNumCompose() }
            composable(Nav.BottomNavScreen.MineScreen.route) { ThemeCompose() }
        }
    )
}








