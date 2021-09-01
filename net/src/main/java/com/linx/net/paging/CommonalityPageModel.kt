package com.linx.net.paging

import androidx.annotation.Keep

/**
 * page公共的Model
 */
@Keep
data class CommonalityPageModel<T>(
    val `data`: Data<T>,
) {
    @Keep
    data class Data<T>(
        val curPage: Int,
        val datas: List<T>,
        val offset: Int,
        val over: Boolean,
        val pageCount: Int,
        val size: Int,
        val total: Int
    )

}