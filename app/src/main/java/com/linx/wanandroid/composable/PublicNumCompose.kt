package com.linx.wanandroid.composable

import androidx.compose.runtime.Composable
import com.linx.common.baseData.statusBarTitle
import com.linx.common.model.StatusBarTitleData

/**
 * 公众号页面
 */
@Composable
fun PublicNumCompose() {

    statusBarTitle.value = StatusBarTitleData("公众号")

}