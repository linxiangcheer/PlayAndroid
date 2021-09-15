package com.linx.playAndroid.composable

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.linx.common.baseData.Nav
import com.linx.playAndroid.KeyNavigationRoute
import com.linx.playAndroid.model.NaviData
import com.linx.playAndroid.model.SystemData
import com.linx.playAndroid.model.UserArticleListData
import com.linx.playAndroid.public.*
import com.linx.playAndroid.viewModel.SquareViewModel

/**
 * 广场页面
 */
@Composable
fun SquareCompose(navHostController: NavHostController) {

    val squareViewModel: SquareViewModel = viewModel()

    val userArticleListData = squareViewModel.userArticleListData.collectAsLazyPagingItems()

    val questionAnswerData = squareViewModel.questionAnswerData.collectAsLazyPagingItems()

    val systemData = squareViewModel.systemData.observeAsState()

    val naviData = squareViewModel.naviData.observeAsState()

    when (Nav.squareTopBarIndex.value) {
        //广场页面
        0 -> {
            SquareAndQuestionComposable(
                navHostController,
                userArticleListData,
                squareViewModel,
                squareViewModel.squareIndexState
            )
        }
        //每日一问页面
        1 -> SquareAndQuestionComposable(
            navHostController,
            questionAnswerData,
            squareViewModel,
            squareViewModel.questionIndexState
        )
        //体系页面
        2 -> {
            if (systemData.value == null) squareViewModel.getSystemData()

            SwipeRefreshContent(
                squareViewModel,
                systemData.value,
                state = squareViewModel.systemIndexState,
                noData = { squareViewModel.getSystemData() },
            ) { data ->
                SystemCardItemContent(data.name ?: "", data.children)
            }
        }
        //导航页面
        else -> {
            if (naviData.value == null) squareViewModel.getNavi()

            SwipeRefreshContent(
                squareViewModel,
                naviData.value,
                state = squareViewModel.naviIndexState,
                noData = { squareViewModel.getNavi() },
            ) { data ->
                NaviCardItemContent(data.name ?: "", data.articles)
            }
        }
    }
}

/**
 * 广场和问答页面
 */
@Composable
private fun SquareAndQuestionComposable(
    navHostController: NavHostController,
    listdata: LazyPagingItems<UserArticleListData>,
    squareViewModel: SquareViewModel,
    state: LazyListState = rememberLazyListState()
) {
    //广场页面广场模块内容
    SwipeRefreshContent(
        squareViewModel,
        listdata,
        state = state
    ) { index, data ->
        data.apply {
            HomeCardItemContent(
                getAuthor(author, shareUser),
                fresh,
                false,
                niceDate ?: "刚刚",
                title ?: "",
                superChapterName ?: "未知",
                collect
            ) {
                navHostController.navigate("${KeyNavigationRoute.WEBVIEW.route}?url=$link")
            }
        }
    }
}

/**
 * 体系页面卡片的内容
 */
@Composable
private fun SystemCardItemContent(title: String, list: List<SystemData.Children?>?) {

    Column(
        //内边距
        modifier = Modifier.fillMaxSize()
            .padding(top = 6.dp, bottom = 6.dp, start = 10.dp, end = 10.dp)
    ) {

        Text(
            title,
            fontWeight = FontWeight.Bold,
            fontSize = 17.sp,
            color = MaterialTheme.colors.secondaryVariant
        )

        //标签
        LabelCustom(itemGap = FlowBoxGap(6.dp)) {
            list?.forEach { data ->
                Button(onClick = {}) { Text(data?.name ?: "") }
            }
        }


    }

}

/**
 * 体系页面卡片的内容
 */
@Composable
private fun NaviCardItemContent(title: String, list: List<NaviData.Article?>?) {

    Column(
        //内边距
        modifier = Modifier.fillMaxSize()
            .padding(top = 6.dp, bottom = 6.dp, start = 10.dp, end = 10.dp)
    ) {

        Text(
            title,
            fontWeight = FontWeight.Bold,
            fontSize = 17.sp,
            color = MaterialTheme.colors.secondaryVariant
        )

        //标签
        LabelCustom(itemGap = FlowBoxGap(start = 0.dp, end = 4.dp, top = 4.dp, bottom = 4.dp)) {

            //没有标签的时候
            if (list == null || list.isEmpty()) {
                Text("暂无", fontWeight = FontWeight.Light)
                return@LabelCustom
            }

            list.forEachIndexed { index, article ->
                Button(onClick = {}) { Text(article?.title ?: "") }
            }
        }


    }

}