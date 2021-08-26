package com.linx.wanandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.MutableLiveData
import com.linx.common.baseData.themeTypeState
import com.linx.common.model.StatusBarTitleData
import com.linx.wanandroid.navigation.MainCompose
import com.linx.wanandroid.ui.theme.CustomThemeManager

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val themeState = themeTypeState.value

            //状态栏
            window.statusBarColor = if (isSystemInDarkTheme()) {
                CustomThemeManager.getWrappedColor(themeState).darkColors.primary
            } else {
                CustomThemeManager.getWrappedColor(themeState).lightColors.primary
            }.toArgb()

            //底部导航栏
            window.navigationBarColor = if (isSystemInDarkTheme()) {
                CustomThemeManager.getWrappedColor(themeState).darkColors.primary
            } else {
                CustomThemeManager.getWrappedColor(themeState).lightColors.primary
            }.toArgb()

            //主题包裹
            CustomThemeManager.WanAndroidTheme(themeState) {
                //主界面
                MainCompose()
            }
        }

    }
}