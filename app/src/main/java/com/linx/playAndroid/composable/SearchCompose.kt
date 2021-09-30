package com.linx.playAndroid.composable

import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.linx.common.ext.toast
import com.linx.playAndroid.public.*
import com.linx.playAndroid.viewModel.SearchViewModel
import com.linx.playAndroid.widget.roomUtil.SearchHistoryData
import com.linx.playAndroid.widget.roomUtil.SearchHistoryHelper
import com.linx.playAndroid.widget.roomUtil.SearchHistoryHelper.delete
import com.linx.playAndroid.widget.roomUtil.SearchHistoryHelper.deleteAll
import com.linx.playAndroid.widget.roomUtil.SearchHistoryHelper.insertSearchHistory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/**
 * 搜索页面
 */
@Composable
fun SearchCompose(navHostController: NavHostController) {

    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    val searchViewModel: SearchViewModel = viewModel()

    //请求热门搜索
    searchViewModel.getHotKeyData()

    var textFieldValue by remember { mutableStateOf(TextFieldValue("")) }

    BaseScreen {
        Scaffold(topBar = {
            AppBar(textFieldValue = textFieldValue, onValueChange = { changeValue: TextFieldValue ->
                textFieldValue = changeValue
            }, leftIcon = Icons.Default.ArrowBack, onLeftClick = {
                navHostController.navigateUp()
            }, rightIcon = Icons.Default.Search, onRightClick = {

                if (textFieldValue.text.isBlank()) {
                    return@AppBar
                }

                //保存到room数据库
                scope.launch {
                    this.insertSearchHistory(context, SearchHistoryData(textFieldValue.text))
                }

                //todo 跳转到搜索结果页面
            })
        }) {
            SearchScrren(context, scope, navHostController, searchViewModel)
        }
    }

}

/**
 * 标题栏下方的内容
 */
@Composable
private fun SearchScrren(
    context: Context,
    scope: CoroutineScope,
    navHostController: NavHostController,
    searchViewModel: SearchViewModel
) {

    //热搜标签
    val hotKeyList = searchViewModel.hotKeyListData.observeAsState().value

    //搜索历史数据
    val searchHistoryListData =
        SearchHistoryHelper.getLiveDataAllSearchHistory(context).observeAsState()

    Column(
        modifier = Modifier.padding(10.dp).fillMaxSize()
    ) {

        TopTextIconScreen("热门搜索")

        LabelCustom(itemGap = FlowBoxGap(start = 0.dp, end = 4.dp, top = 4.dp, bottom = 4.dp)) {
            if (hotKeyList?.isEmpty() == true || hotKeyList == null) {
                Text(
                    "暂无热词",
                    fontWeight = FontWeight.Light,
                    color = MaterialTheme.colors.secondaryVariant
                )
                return@LabelCustom
            }

            hotKeyList.forEachIndexed { index, hotKeyData ->
                Button(onClick = {}) { Text(hotKeyData.name ?: "") }
            }
        }

        TopTextIconScreen("搜索历史", true) {
            scope.launch {
                //删除数据库内的数据
                deleteAll(context)
                "搜索历史记录已清除".toast(context)
            }
        }

        //搜索历史列表数据，这里传入倒叙后的数据list
        searchHistoryListData.value?.let { SearchHistoryScreen(context, it.reversed()) }

    }

}

/**
 * 顶部的内容
 */
@Composable
private fun TopTextIconScreen(
    text: String = "",
    //是否显示删除图标
    showDeleteIcon: Boolean = false,
    //删除图标点击事件
    deleteClick: () -> Unit = {}
) {
    Row(
        modifier = Modifier.height(30.dp).fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text, fontSize = 18.sp, color = MaterialTheme.colors.primary)

        if (showDeleteIcon) {
            Icon(
                Icons.Default.Delete,
                contentDescription = null,
                modifier = Modifier.clickable(
                    onClick = deleteClick,
                    indication = null,
                    interactionSource = MutableInteractionSource()
                ).size(16.dp),
                tint = MaterialTheme.colors.primary
            )
        }
    }
}

/**
 * 搜索历史列表
 */
@Composable
private fun SearchHistoryScreen(context: Context, list: List<SearchHistoryData>) {
    LazyColumn {
        itemsIndexed(list) { index: Int, item: SearchHistoryData ->
            RowTextIcon(item.text, paddingTop = 10.dp) {
                delete(context, item.id ?: 0)
                "已删除搜索历史：${item.text}".toast(context)
            }
        }
    }
}