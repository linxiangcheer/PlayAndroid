package com.linx.playAndroid.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemsIndexed
import coil.compose.rememberImagePainter
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.linx.common.base.BaseViewModel
import com.linx.common.baseData.Nav
import com.linx.common.ext.transitionDate
import com.linx.common.widget.sleepTime
import com.linx.playAndroid.model.ProjectListData
import com.linx.playAndroid.public.HomeCard
import com.linx.playAndroid.public.paging.pagingStateUtil
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

    //内容
    ProjectContent(projectViewModel, projectListData)

}

/**
 * 项目页面列表子项
 */
@Composable
private fun ProjectItemContent(project: ProjectListData) {

    Column(modifier = Modifier.padding(start = 8.dp, end = 8.dp)) {

        topCard(project.author ?: "", project.publishTime.transitionDate())

        Row(
            modifier = Modifier.weight(1f, true).padding(top = 6.dp)
        ) {
            centerCard(project.envelopePic ?: "", project.title ?: "", project.desc ?: "")
        }

        bottomCard(
            "开源项目主Tab·${project.chapterName}",
            project.collect,
            modifier = Modifier.padding(bottom = 6.dp)
        )
    }
}

/**
 * Card顶部部分
 */
@Composable
private fun topCard(author: String, desc: String) {

    Row(
        modifier = Modifier.padding(top = 6.dp).fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = author,
            style = MaterialTheme.typography.body1,
            color = Color.Gray
        )
        Text(
            text = desc,
            style = MaterialTheme.typography.body1,
            color = Color.Gray,
            modifier = Modifier.padding(start = 10.dp)
        )
    }

}

/**
 * Card中间部分
 */
@Composable
private fun centerCard(envelopePic: String, title: String, desc: String) {

    val painter = rememberImagePainter(data = envelopePic)

    Image(
        painter = painter,
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier.size(120.dp)
            .padding(end = 4.dp)
    )

    Column {
        Text(
            text = title,
            style = MaterialTheme.typography.h6,
            maxLines = 2,
            fontSize = 17.sp,
            //超长以...结尾
            overflow = TextOverflow.Ellipsis
        )

        Text(
            text = desc,
            style = MaterialTheme.typography.body1,
            color = Color.Gray,
            modifier = Modifier.padding(top = 6.dp).weight(1f, true),
            maxLines = 4,
            fontSize = 14.sp,
            //超长以...结尾
            overflow = TextOverflow.Ellipsis
        )
    }
}

/**
 * Card底部部分
 */
@Composable
private fun bottomCard(
    //渠道名
    chapterName: String,
    //是否收藏
    collect: Boolean,
    modifier: Modifier = Modifier
) {

    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(chapterName, color = Color.Gray, fontSize = 12.sp)
        //收藏
        Icon(
            Icons.Default.Favorite,
            contentDescription = null,
            tint = if (collect) Color.Red else Color.LightGray
        )
    }

}

/**
 * 项目页面的内容
 */
@Composable
private fun ProjectContent(
    projectViewModel: ProjectViewModel,
    projectListData: LazyPagingItems<ProjectListData>
) {

    //下拉刷新头状态
    val refreshState = rememberSwipeRefreshState(false)

    SwipeRefresh(
        state = refreshState,
        onRefresh = {
            //显示刷新头
            refreshState.isRefreshing = true
            projectViewModel.sleepTime(3000) {
                refreshState.isRefreshing = false
            }
        }
    ) {
        pagingStateUtil(projectListData, refreshState, projectViewModel) {
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                itemsIndexed(projectListData) { index, item ->
                    HomeCard(180.dp) {
                        ProjectItemContent(item!!)
                    }
                }
            }
        }
    }
}