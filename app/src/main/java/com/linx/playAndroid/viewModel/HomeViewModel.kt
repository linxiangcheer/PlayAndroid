package com.linx.playAndroid.viewModel

import android.util.Log
import androidx.compose.foundation.lazy.LazyListState
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.linx.common.base.BaseViewModel
import com.linx.common.baseData.CommonConstant
import com.linx.common.widget.SpUtilsMMKV
import com.linx.net.ext.*
import com.linx.net.paging.CommonPagingSource
import com.linx.playAndroid.model.ArticleListData
import com.linx.playAndroid.model.BannerData
import com.linx.playAndroid.model.HomeBannerData
import com.linx.playAndroid.repo.HomeRepo
import kotlinx.coroutines.flow.Flow

/**
 * 首页
 */
class HomeViewModel : BaseViewModel() {

    //首页列表状态
    val homeLazyListState: LazyListState = LazyListState()

    //轮播图的数据
    private val _bannerListData = MutableLiveData<List<BannerData>>()
    val bannerListData: LiveData<List<BannerData>>
        get() = _bannerListData

    /**
     * 获取首页轮播图
     */
    fun getBannerData() = serverAwait {
        HomeRepo.getBanner().serverData().onSuccess {
            onBizError { code, message ->
                Log.e("xxx", "获取首页轮播图 接口异常 $code $message")
            }
            onBizOK<List<HomeBannerData>> { code, data, message ->
                //转换为轮播图数据
                val bannerList = mutableListOf<BannerData>()
                data?.forEach { data ->
                    bannerList.add(BannerData(data.imagePath.toString(), data.url.toString()))
                }
                _bannerListData.postValue(bannerList)
            }
        }.onFailure {
            Log.e("xxx", "获取首页轮播图 接口异常 $it")
        }
    }

    //首页列表
    val homeListData: Flow<PagingData<ArticleListData>>
        get() = _homeListData

    private val _homeListData = Pager(PagingConfig(pageSize = 20)) {
        CommonPagingSource { nextPage: Int ->
            HomeRepo.getHomeList(nextPage)
        }
    }.flow.cachedIn(viewModelScope)

    //置顶文章列表数据
    private val _articleTopList = MutableLiveData<List<ArticleListData>>()
    val articleTopList: LiveData<List<ArticleListData>>
        get() = _articleTopList

    /**
     * 获取置顶文章列表
     */
    fun getArticleTopListData() = serverAwait {

        //是否隐藏置顶文章
        if (SpUtilsMMKV.getBoolean(CommonConstant.GONE_ARTICLE_TOP) == true) {
            _articleTopList.postValue(null)
            return@serverAwait
        }

        HomeRepo.getArticleTopList().serverData().onSuccess {
            onBizError { code, message ->
                Log.e("xxx", "获取置顶文章列表 接口异常 $code $message")
            }
            onBizOK<List<ArticleListData>> { code, data, message ->
                _articleTopList.postValue(data)
            }
        }.onFailure {
            Log.e("xxx", "获取置顶文章列表 接口异常 $it")
        }
    }

}