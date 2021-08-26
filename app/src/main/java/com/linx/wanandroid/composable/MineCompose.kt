package com.linx.wanandroid.composable

import androidx.compose.runtime.Composable
import com.linx.common.baseData.statusBarTitle
import com.linx.common.model.StatusBarTitleData

/**
 * 我的页面
 */
@Composable
fun MineCompose() {

    statusBarTitle.value = StatusBarTitleData("我的")

}