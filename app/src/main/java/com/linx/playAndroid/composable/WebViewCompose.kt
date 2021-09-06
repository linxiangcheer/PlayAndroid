package com.linx.playAndroid.composable

import android.content.Context
import android.util.Log
import android.webkit.WebView
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.linx.playAndroid.public.AppBar
import com.linx.playAndroid.public.BaseScreen

/**
 * H5页面
 */
@Composable
fun WebViewCompose(navController: NavController, url: String) {

    BaseScreen {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {

            AppBar("玩Android", leftIcon = Icons.Default.ArrowBack, onLeftClick = {
                navController.navigateUp()
            })

            AndroidView({ context: Context ->
                WebView(context).apply {
                    loadUrl(url)
                }
            })
        }
    }

}