package com.linx.playAndroid.composable

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.paging.compose.LazyPagingItems
import com.linx.playAndroid.model.ProjectTreeData
import com.linx.playAndroid.viewModel.ProjectViewModel

/**
 * 项目页面
 */
@Composable
fun ProjectCompose(navController: NavController) {

    val projectViewModel: ProjectViewModel = viewModel()

    initRequest(projectViewModel)

    val projectTreeData = projectViewModel.projectTreeData.observeAsState()

    //顶部指示器
    projectTab(projectTreeData.value, projectViewModel)

}

/**
 * 顶部的指示器
 * [projects]指示器下方的内容
 */
@Composable
private fun projectTab(data: List<ProjectTreeData>?, viewModel: ProjectViewModel) {

    if (data == null) {
        Box(
            modifier = Modifier.background(MaterialTheme.colors.primary).fillMaxWidth()
                .height(54.dp)
        )
        return
    }

    ScrollableTabRow(
        selectedTabIndex = viewModel.projectTreeIndex.value,
        modifier = Modifier.fillMaxWidth().height(50.dp),
        edgePadding = 0.dp,
        backgroundColor = MaterialTheme.colors.primary
    ) {
        data.forEachIndexed { index, item ->
            Tab(
                text = { Text(item.name ?: "") },
                selected = viewModel.projectTreeIndex.value == index,
                onClick = {
                    viewModel.apply {
                        projectTreeIndex.value = index
                        //todo paging内容刷新 指示器下方的内容
                    }
                }
            )
        }
    }
}

/**
 * 初始化网络请求内容
 */
private fun initRequest(projectViewModel: ProjectViewModel) {

    //获取项目页面顶部指示器数据
    projectViewModel.getProjectTreeData()

}