package com.linx.common.baseData

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.linx.common.model.ThemeType

/**
 * 标题栏和导航栏颜色
 */
val themeTypeState: MutableState<ThemeType> by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
    mutableStateOf(ThemeType.Default)
}

/**
 * 刷新个人信息数据
 */
val refreshUserMessageData: MutableState<String> by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
    mutableStateOf("")
}