package com.linx.playAndroid.model
import androidx.annotation.Keep

import com.google.gson.annotations.SerializedName

/**
 * 我的文章页面的数据model
 */
@Keep
data class MyShareArticlesListData<T>(
    val `data`: Data<T>,
    val errorCode: Int,
    val errorMsg: String?
) {
    @Keep
    data class Data<T>(
        val coinInfo: CoinInfo?,
        val shareArticles: ShareArticles<T>
    ) {
        @Keep
        data class CoinInfo(
            val coinCount: Int,
            val level: Int,
            val nickname: String?,
            val rank: String?,
            val userId: Int,
            val username: String?
        )

        @Keep
        data class ShareArticles<T>(
            val curPage: Int,
            val datas: List<T>,
            val offset: Int,
            val over: Boolean,
            val pageCount: Int,
            val size: Int,
            val total: Int
        )
    }
}