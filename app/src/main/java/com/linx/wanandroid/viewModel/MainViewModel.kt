package com.linx.wanandroid.viewModel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.linx.common.base.BaseViewModel
import com.linx.wanandroid.ui.theme.ThemeType

class MainViewModel: BaseViewModel() {

    //标题栏和导航栏颜色
    val themeTypeState = mutableStateOf(ThemeType.Default)

}