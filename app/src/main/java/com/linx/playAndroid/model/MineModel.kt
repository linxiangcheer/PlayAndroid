package com.linx.playAndroid.model

import androidx.annotation.Keep

/**
 * 个人积分数据
 */
@Keep
data class UserInfoIntegralData(
    val coinCount: Int,
    val level: Int,
    val nickname: String?,
    val rank: String?,
    val userId: Int,
    val username: String?
)