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

/**
 * 项目列表数据
 */
@Keep
data class ProjectListData(
    val apkLink: String?,
    val audit: Int,
    val author: String?,
    val canEdit: Boolean,
    val chapterId: Int,
    val chapterName: String?,
    val collect: Boolean,
    val courseId: Int,
    val desc: String?,
    val descMd: String?,
    val envelopePic: String?,
    val fresh: Boolean,
    val host: String?,
    val id: Int,
    val link: String?,
    val niceDate: String?,
    val niceShareDate: String?,
    val origin: String?,
    val prefix: String?,
    val projectLink: String?,
    val publishTime: Long,
    val realSuperChapterId: Int,
    val selfVisible: Int,
    val shareDate: Long,
    val shareUser: String?,
    val superChapterId: Int,
    val superChapterName: String?,
    val tags: List<Tag?>?,
    val title: String?,
    val type: Int,
    val userId: Int,
    val visible: Int,
    val zan: Int
) {
    @Keep
    data class Tag(
        val name: String?,
        val url: String?
    )
}