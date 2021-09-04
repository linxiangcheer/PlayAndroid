package com.linx.playAndroid.public

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.google.accompanist.insets.navigationBarsHeight
import com.google.accompanist.insets.statusBarsHeight

/**
 * 在不想使用透明状态栏/导航栏的时候
 * 用这个作为基础的布局可以让内容不被状态栏/导航栏遮挡
 */
@Composable
fun BaseScreen(content: @Composable () -> Unit) {

    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        //内容不挡住状态栏 如果不设置颜色这里会自己适配，但可能产生闪烁
        Spacer(modifier = Modifier.background(MaterialTheme.colors.primary).statusBarsHeight().fillMaxWidth())

        content()

        //内容不挡住导航栏 如果不设置颜色这里会自己适配，但可能产生闪烁
        Spacer(modifier = Modifier.background(MaterialTheme.colors.primary).navigationBarsHeight().fillMaxWidth())

    }

}