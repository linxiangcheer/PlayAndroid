package com.linx.playAndroid.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


/**
 * 广场/ 问答数据
 */
@Keep
data class UserArticleListData(
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
    val tags: List<Any?>?,
    val title: String?,
    val type: Int,
    val userId: Int,
    val visible: Int,
    val zan: Int
)

/**
 * 体系数据
 */
@Keep
data class SystemData(
    val children: List<Children?>?,
    val courseId: Int,
    val id: Int,
    //体系大项名称
    val name: String?,
    val order: Int,
    val parentChapterId: Int,
    val userControlSetTop: Boolean,
    val visible: Int
) {
    @Keep
    data class Children(
        val children: List<Any?>?,
        val courseId: Int,
        val id: Int,
        //体系子项名称
        val name: String?,
        val order: Int,
        val parentChapterId: Int,
        val userControlSetTop: Boolean,
        val visible: Int
    )
}

/**
 * 导航数据
 */
@Keep
data class NaviData(
    val articles: List<Article?>?,
    val cid: Int,
    val name: String?
) {
    @Keep
    data class Article(
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
        val shareDate: Any?,
        val shareUser: String?,
        val superChapterId: Int,
        val superChapterName: String?,
        val tags: List<Any?>?,
        val title: String?,
        val type: Int,
        val userId: Int,
        val visible: Int,
        val zan: Int
    )
}