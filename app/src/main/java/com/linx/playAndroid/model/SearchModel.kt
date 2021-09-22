package com.linx.playAndroid.model

import androidx.annotation.Keep

/**
 * 热词
 */
@Keep
data class HotKeyData(
    val id: Int,
    //链接
    val link: String?,
    //关键字
    val name: String?,
    val order: Int,
    val visible: Int
)