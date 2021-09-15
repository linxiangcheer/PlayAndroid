package com.linx.playAndroid.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import com.linx.common.baseData.Nav
import com.linx.common.ext.transitionDate
import com.linx.playAndroid.KeyNavigationRoute
import com.linx.playAndroid.model.ProjectListData
import com.linx.playAndroid.public.SwipeRefreshContent
import com.linx.playAndroid.public.BottomCard
import com.linx.playAndroid.public.CenterCard
import com.linx.playAndroid.public.TopCard
import com.linx.playAndroid.viewModel.ProjectViewModel

/**
 * 项目页面
 */
@Composable
fun ProjectCompose(navHostController: NavHostController) {

    val projectViewModel: ProjectViewModel = viewModel()

    //项目列表数据
    val projectListData = projectViewModel.projectListData.collectAsLazyPagingItems()

    //TopBar的Index改变
    LaunchedEffect(Nav.projectTopBarIndex.value) {

        if (Nav.projectTopBarIndex.value == projectViewModel.saveChangeProjectIndex) return@LaunchedEffect

        projectViewModel.apply {
            //保存改变过index和offset的指示器Index
            saveChangeProjectIndex = Nav.projectTopBarIndex.value
            projectLazyListState.scrollToItem(0, 0)
        }

        projectListData.refresh()
    }

    //项目页面的内容
    SwipeRefreshContent(
        projectViewModel,
        projectListData,
        state = projectViewModel.projectLazyListState,
        cardHeight = 190.dp
    ) { index, data ->
        ProjectItemContent(data) {
            navHostController.navigate("${KeyNavigationRoute.WEBVIEW.route}?url=${data.link}")
        }
    }

}

/**
 * 项目页面列表子项
 */
@Composable
private fun ProjectItemContent(project: ProjectListData, onClick: () -> Unit) {

    Column(
        modifier = Modifier.clickable(onClick = onClick).padding(bottom = 6.dp, top = 6.dp)
            .padding(start = 8.dp, end = 8.dp)
    ) {

        TopCard(project.author ?: "", project.publishTime.transitionDate())

        Row(
            modifier = Modifier.padding(3.dp).weight(1f)
        ) {
            CenterCard(project.envelopePic ?: "", project.title ?: "", project.desc ?: "")
        }

        BottomCard(
            "${project.superChapterName}·${project.chapterName}",
            project.collect
        )
    }

}

