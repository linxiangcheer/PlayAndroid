package com.linx.playAndroid.composable

import androidx.compose.foundation.layout.*
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
import com.linx.playAndroid.public.AppBar
import com.linx.playAndroid.public.BaseScreen
import com.linx.playAndroid.public.FlowBoxGap
import com.linx.playAndroid.public.LabelCustom
import com.linx.playAndroid.viewModel.SearchViewModel

/**
 * 搜索页面
 */
@Composable
fun SearchCompose(navHostController: NavHostController) {

    val searchViewModel: SearchViewModel = viewModel()

    searchViewModel.getHotKeyData()

    var textFieldValue by remember { mutableStateOf(TextFieldValue("")) }

    val context = LocalContext.current

    BaseScreen {
        Scaffold(topBar = {
            AppBar(textFieldValue = textFieldValue, onValueChange = { changeValue: TextFieldValue ->
                textFieldValue = changeValue
            }, leftIcon = Icons.Default.ArrowBack, onLeftClick = {
                navHostController.navigateUp()
            }, rightIcon = Icons.Default.Search, onRightClick = {
                //todo 跳转到搜索结果页面
                "跳转到搜索页面".toast(context)
            })
        }) {
            SearchScrren(navHostController, searchViewModel)
        }
    }

}

/**
 * 标题栏下方的内容
 */
@Composable
private fun SearchScrren(navHostController: NavHostController, searchViewModel: SearchViewModel) {

    val hotKeyList = searchViewModel.hotKeyListData.observeAsState().value

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

        TopTextIconScreen("搜索历史", true)

    }

}

/**
 * 顶部的内容
 */
@Composable
private fun TopTextIconScreen(
    text: String = "",
    //是否显示删除图标
    showDeleteIcon: Boolean = false
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
                modifier = Modifier.size(16.dp),
                tint = MaterialTheme.colors.primary
            )
        }
    }
}