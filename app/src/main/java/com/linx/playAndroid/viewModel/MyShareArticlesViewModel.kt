package com.linx.playAndroid.viewModel

import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.linx.common.base.BaseViewModel
import com.linx.net.paging.CommonPagingSource
import com.linx.net.paging.CommonalityPageModel
import com.linx.playAndroid.model.MyCollectData
import com.linx.playAndroid.model.MyShareArticlesListData
import com.linx.playAndroid.repo.MyShareArticlesRepo
import kotlinx.coroutines.flow.Flow
import java.lang.Exception

/**
 * 我的文章
 */
class MyShareArticlesViewModel : BaseViewModel() {

    //我的文章列表数据
    val myShareArticlesListData: Flow<PagingData<MyCollectData>>
        get() = _myShareArticlesListData

    //获取我的文章列表数据
    private val _myShareArticlesListData = Pager(PagingConfig(pageSize = 20)) {
        MyShareArticlesListDataSource { nextPage ->
            MyShareArticlesRepo.getMyShareArticles((nextPage + 1))
        }
    }.flow.cachedIn(viewModelScope)


}

/**
 * 我分享的文章列表的Paging数据源
 */
class MyShareArticlesListDataSource <T: Any> (private val block: suspend (nextPage: Int) -> MyShareArticlesListData<T>): PagingSource<Int, T>() {

    override fun getRefreshKey(state: PagingState<Int, T>): Int?  = null

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, T> {
        return try {
            //params.key为当前页码 页码从0开始
            val nextPage = params.key ?: 0
            //更新页码后请求数据
            val response = block.invoke(nextPage)
            LoadResult.Page(
                data = response.data.shareArticles.datas,
                //前一页页码
                prevKey = if (nextPage == 0) null else nextPage - 1,
                //后一页页码
                nextKey = if (nextPage < response.data.shareArticles.pageCount) nextPage + 1 else null
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

}