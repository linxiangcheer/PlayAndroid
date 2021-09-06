package com.linx.common.baseData

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.linx.common.model.ThemeType
import com.linx.common.widget.SpUtilsMMKV

val themeColorList = listOf(
    ThemeType.Default,
    ThemeType.Theme1,
    ThemeType.Theme2,
    ThemeType.Theme3,
    ThemeType.Theme4,
    ThemeType.Theme5,
)

/**
 * 标题栏和导航栏颜色
 */
val themeTypeState: MutableState<ThemeType> by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
    mutableStateOf(getThemeType())
}

/**
 * 获取保存下来的主题颜色
 */
private fun getThemeType(): ThemeType {
    return when(SpUtilsMMKV.getInt(CommonConstant.THEME_COLOR) ?: 0) {
        0 -> ThemeType.Default
        1 -> ThemeType.Theme1
        2 -> ThemeType.Theme2
        3 -> ThemeType.Theme3
        4 -> ThemeType.Theme4
        5 -> ThemeType.Theme5
        else -> ThemeType.Theme5
    }
}

/**
 * 刷新个人信息数据
 */
val refreshUserMessageData: MutableState<String> by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
    mutableStateOf("")
}