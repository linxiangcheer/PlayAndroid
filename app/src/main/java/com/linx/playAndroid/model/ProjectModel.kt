package com.linx.playAndroid.model

import androidx.annotation.Keep

/**
 * 项目页面的指示条
 */
@Keep
data class ProjectTreeData(
    val children: List<Any?>?,
    val courseId: Int,
    //该id在获取该分类下项目时需要用到
    val id: Int,
    //分类名称
    val name: String?,
    val order: Int,
    val parentChapterId: Int,
    val userControlSetTop: Boolean,
    val visible: Int
)