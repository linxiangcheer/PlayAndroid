package com.linx.common.model

import androidx.compose.ui.graphics.vector.ImageVector

/**
 * 标题栏的数据
 */
data class StatusBarTitleData(
    val title: String,
    val leftIcon: ImageVector? = null,
    val rightIcon: ImageVector? = null,
    val onLeftClick: () -> Unit = {},
    val onRightClick: () -> Unit = {}
)