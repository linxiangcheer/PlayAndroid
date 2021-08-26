package com.linx.wanandroid.composable

import androidx.compose.runtime.Composable
import com.linx.common.baseData.statusBarTitle
import com.linx.common.model.StatusBarTitleData

/**
 * 广场页面
 */
@Composable
fun SquareCompose() {

    statusBarTitle.value = StatusBarTitleData("广场")

}