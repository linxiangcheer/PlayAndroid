package com.linx.playAndroid.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import com.linx.common.baseData.Nav
import com.linx.playAndroid.KeyNavigationRoute
import com.linx.playAndroid.model.NaviData
import com.linx.playAndroid.model.SystemData
import com.linx.playAndroid.public.*
import com.linx.playAndroid.viewModel.SquareViewModel

/**
 * 广场页面
 */
@Composable
fun SquareCompose(navHostController: NavHostController) {

    val squareViewModel: SquareViewModel = viewModel()

    //广场和问答页面
    if (Nav.squareTopBarIndex.value == 0 || Nav.squareTopBarIndex.value == 1) {
        SquareAndQuestionComposable(navHostController, Nav.squareTopBarIndex.value, squareViewModel)
    }

    val systemData = squareViewModel.systemData.observeAsState()

    //体系页面
    if (Nav.squareTopBarIndex.value == 2) {
        //请求体系数据
        if (systemData.value == null) squareViewModel.getSystemData()

        SwipeRefreshContent(
            squareViewModel,
            systemData.value,
            noData = { squareViewModel.getSystemData() },
        ) { data ->
            SystemCardItemContent(data.name ?: "", data.children)
        }
    }

    val naviData = squareViewModel.naviData.observeAsState()

    //导航页面
    if (Nav.squareTopBarIndex.value == 3) {
        //请求导航数据
        if (naviData.value == null) squareViewModel.getNavi()

        SwipeRefreshContent(
            squareViewModel,
            naviData.value,
            noData = { squareViewModel.getNavi() },
        ) { data ->
            NaviCardItemContent(data.name ?: "", data.articles)
        }
    }

}

/**
 * 广场和问答页面
 */
@Composable
private fun SquareAndQuestionComposable(
    navHostController: NavHostController,
    index: Int,
    squareViewModel: SquareViewModel
) {

    //广场数据
    val userArticleListData = squareViewModel.userArticleListData.collectAsLazyPagingItems()

    //问答数据
    val questionAnswerData = squareViewModel.questionAnswerData.collectAsLazyPagingItems()

    //广场页面广场模块内容
    SwipeRefreshContent(
        squareViewModel,
        when (index) {
            0 -> userArticleListData
            else -> questionAnswerData
        }
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