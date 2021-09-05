package com.linx.net.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import java.lang.Exception

/**
 * 公共的Paging数据源
 */
class CommonPagingSource <T: Any> (private val block: suspend (nextPage: Int) -> CommonalityPageModel<T>): PagingSource<Int, T>() {

    override fun getRefreshKey(state: PagingState<Int, T>): Int?  = null

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, T> {
        return try {
            //params.key为当前页码 页码从0开始
            val nextPage = params.key ?: 0
            //更新页码后请求数据
            val response = block.invoke(nextPage)
            LoadResult.Page(
                data = response.data.datas,
                //前一页页码
                prevKey = if (nextPage == 0) null else nextPage - 1,
                //后一页页码
                nextKey = if (nextPage < response.data.pageCount) nextPage + 1 else null
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

}