package com.linx.playAndroid.model

import androidx.annotation.Keep

/**
 * 积分排行
 */
@Keep
data class CoinRankData(
    val coinCount: Int,
    val level: Int,
    val nickname: String?,
    val rank: String?,
    val userId: Int,
    val username: String?
)