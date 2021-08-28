package com.linx.playAndroid

import android.os.Bundle
import android.view.KeyEvent
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.ui.graphics.toArgb
import com.linx.common.baseData.themeTypeState
import com.linx.playAndroid.navigation.MainCompose
import com.linx.playAndroid.ui.theme.CustomThemeManager
import com.linx.common.widget.TwoBackFinish

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
                CustomThemeManager.getWrappedColor(themeState).darkColors.background
            } else {
                CustomThemeManager.getWrappedColor(themeState).lightColors.background
            }.toArgb()

            //主题包裹
            CustomThemeManager.WanAndroidTheme(themeState) {
                //主界面
                MainCompose()
            }
        }

    }

    //拦截返回按钮，点击两次才关闭app
    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean = TwoBackFinish().execute(keyCode, this)

}