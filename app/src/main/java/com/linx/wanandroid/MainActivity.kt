package com.linx.wanandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.linx.wanandroid.navigation.NavScreen
import com.linx.wanandroid.public.Page
import com.linx.wanandroid.ui.theme.CustomThemeManager
import com.linx.wanandroid.viewModel.MainViewModel

val statusBarTitle = MutableLiveData<String>()
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val viewModel: MainViewModel = viewModel()

            val typeState = viewModel.themeTypeState.value

            val rememberTitle: String by statusBarTitle.observeAsState("首页")

            //状态栏
            window.statusBarColor = if (isSystemInDarkTheme()) {
                CustomThemeManager.getWrappedColor(typeState).darkColors.primary
            } else {
                CustomThemeManager.getWrappedColor(typeState).lightColors.primary
            }.toArgb()

            //底部导航栏
            window.navigationBarColor = if (isSystemInDarkTheme()) {
                CustomThemeManager.getWrappedColor(typeState).darkColors.primary
            } else {
                CustomThemeManager.getWrappedColor(typeState).lightColors.primary
            }.toArgb()

            //内容
            Page(rememberTitle, typeState, leftIcon = Icons.Default.Favorite, rightIcon = Icons.Default.Search) {
                MainNavScreen()
            }

        }
    }

}

/**
 * 内容
 */
@Composable
fun MainNavScreen() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = NavScreen.main) {
        composable(NavScreen.main) { MainCompose(navController) }
        composable(NavScreen.theme) { ThemeCompose() }
    }
}