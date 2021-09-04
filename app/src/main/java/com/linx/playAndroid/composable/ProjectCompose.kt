package com.linx.playAndroid.composable

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.linx.common.baseData.Nav
import com.linx.common.ext.transitionDate
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
fun ProjectCompose(navController: NavController) {

    val projectViewModel: ProjectViewModel = viewModel()

    //项目列表数据
    val projectListData = projectViewModel.projectListData.collectAsLazyPagingItems()

    //TopBar的Index改变
    LaunchedEffect(Nav.projectTopBarIndex.value) {
        projectListData.refresh()
    }

    //项目页面的内容
    SwipeRefreshContent(projectViewModel, projectListData, cardHeight = 190.dp) { index, data ->
        ProjectItemContent(data)
    }

}

/**
 * 项目页面列表子项
 */
@Composable
private fun ProjectItemContent(project: ProjectListData) {

    Column(
        modifier = Modifier.padding(bottom = 6.dp, top = 6.dp).padding(start = 8.dp, end = 8.dp)
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

