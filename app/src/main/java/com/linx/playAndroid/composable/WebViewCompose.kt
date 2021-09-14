package com.linx.playAndroid.composable

import android.content.Context
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.webkit.WebView
import android.widget.LinearLayout
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavHostController
import com.linx.playAndroid.public.AppBar
import com.linx.playAndroid.public.BaseScreen

/**
 * H5页面
 */
@Composable
fun WebViewCompose(navHostController: NavHostController, url: String) {

    BaseScreen {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {

            AppBar("玩Android", leftIcon = Icons.Default.ArrowBack, onLeftClick = {
                navHostController.navigateUp()
            })

            AndroidView({ context: Context ->
                WebView(context).apply {
                    layoutParams = LinearLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT)
                }
            }, update = {
                //update方法是一个callback, inflate之后会执行, 读取的状态state值变化后也会被执行
                it.apply {
                    loadUrl(url)
                }
            })
        }
    }

}

