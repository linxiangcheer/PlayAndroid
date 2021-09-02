package com.linx.playAndroid.model

import androidx.annotation.Keep

/**
 * 公众号列表数据
 */
@Keep
data class PublicNumChapterData(
    val children: List<Any?>?,
    val courseId: Int,
    val id: Int,
    val name: String?,
    val order: Int,
    val parentChapterId: Int,
    val userControlSetTop: Boolean,
    val visible: Int
)