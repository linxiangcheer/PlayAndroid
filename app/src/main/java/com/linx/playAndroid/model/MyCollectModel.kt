package com.linx.playAndroid.model

import androidx.annotation.Keep

/**
 * 我的收藏列表数据
 */
@Keep
data class MyCollectData(
    val author: String?,
    val chapterId: Int,
    val chapterName: String?,
    val courseId: Int,
    val desc: String?,
    val envelopePic: String?,
    val id: Int,
    val link: String?,
    val niceDate: String?,
    val origin: String?,
    val originId: Int,
    val publishTime: Long,
    val title: String?,
    val userId: Int,
    val visible: Int,
    val zan: Int
)