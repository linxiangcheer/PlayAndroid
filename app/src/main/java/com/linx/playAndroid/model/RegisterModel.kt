package com.linx.playAndroid.model

import androidx.annotation.Keep

/**
 * 注册
 */
@Keep
data class RegisterDaat(
    val admin: Boolean,
    val chapterTops: List<Any?>?,
    val coinCount: Int,
    val collectIds: List<Any?>?,
    val email: String?,
    val icon: String?,
    val id: Int,
    val nickname: String?,
    val password: String?,
    val publicName: String?,
    val token: String?,
    val type: Int,
    val username: String?
)