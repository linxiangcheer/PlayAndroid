package com.linx.playAndroid.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.linx.common.base.BaseViewModel
import com.linx.common.baseData.CommonConstant
import com.linx.common.ext.toast
import com.linx.common.widget.SpUtilsMMKV
import com.linx.net.widget.DataStoreUtils
import com.linx.playAndroid.public.AppBar
import com.linx.playAndroid.public.BaseScreen
import com.linx.playAndroid.public.ColumnTextText
import com.linx.playAndroid.public.SimpleAlertDialog
import com.linx.playAndroid.viewModel.SettingViewModel
import com.linx.playAndroid.widget.CacheDataManager
import com.linx.playAndroid.widget.SmallUtil

/**
 * 设置页面
 */
@Composable
fun SettingCompose(navController: NavHostController) {

    val settingViewModel: SettingViewModel = viewModel()

    BaseScreen {
        Scaffold(topBar = {
            AppBar("设置", leftIcon = Icons.Default.ArrowBack, onLeftClick = {
                navController.navigateUp()
            })
        },
            content = { paddingValues: PaddingValues ->
                SettingCenterScreen(navController, settingViewModel)
            }
        )
    }

}

/**
 * 中间的内容布局
 */
@Composable
private fun SettingCenterScreen(navController: NavHostController, settingViewModel: SettingViewModel) {

    val context = LocalContext.current

    //清除缓存弹窗
    var cacheDataState by remember { mutableStateOf(false) }
    if (cacheDataState) {
        //弹窗,关闭之后重新设置为false,下次可以继续监听
        SimpleAlertDialog("温馨提示", "确定清理缓存吗", confirmStr = "清理", confirmClick = {
            CacheDataManager.clearAllCache(context)
        }) { cacheDataState = false }
    }

    //退出登录弹窗
    var exitLoginState by remember { mutableStateOf(false) }
    if (exitLoginState) {
        SimpleAlertDialog("温馨提示", "确定退出登录吗", confirmStr = "退出", confirmClick = {
            //同步清除cookie
            DataStoreUtils.clearSync()
            //设置为未登录
            SpUtilsMMKV.removeKey(CommonConstant.IS_LOGIN)
            navController.navigateUp()
        }) { exitLoginState = false }
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        item {
            TopText("基本设置")
            ColumnTextTextScreen("清除缓存", CacheDataManager.getTotalCacheSize(context), onClick = {
                cacheDataState = true
            })
            ColumnTextTextScreen("退出", "退出登录", onClick = {
                if (settingViewModel.isLogin())
                    exitLoginState = true
                else "您还未登录".toast(context)
            })
            //分割线
            Spacer(modifier = Modifier.background(Color.Gray).fillMaxWidth().height(1.dp))

            TopText("其他设置")
            ColumnTextTextScreen("主题颜色", "设置主题颜色", context = {
                Surface(
                    modifier = Modifier.size(30.dp),
                    shape = CircleShape,
                    color = MaterialTheme.colors.primary,
                    content = {}
                )
            })
            //分割线
            Spacer(modifier = Modifier.background(Color.Gray).fillMaxWidth().height(1.dp))

            TopText("关于")
            ColumnTextTextScreen("版本", "当前版本：${SmallUtil.packageCode(context)}")
            ColumnTextTextScreen("作者", "LinXiang")
            ColumnTextTextScreen("项目源码", "https://github.com/linxiangcheer/PlayAndroid")
            ColumnTextTextScreen("版权声明", "仅供个人及非商业用途使用")
        }
    }

}

/**
 * 列表第一个Text 主题颜色
 */
@Composable
private fun TopText(text: String) {
    Text(
        text,
        fontSize = 14.sp,
        color = MaterialTheme.colors.primary,
        modifier = Modifier.padding(start = 20.dp, top = 13.dp, bottom = 13.dp)
    )
}

/**
 * 上下文字列表的布局
 */
@Composable
private fun ColumnTextTextScreen(
    topStr: String,
    bottomStr: String,
    paddingTop: Dp = 13.dp,
    paddingBottom: Dp = 13.dp,
    onClick: () -> Unit = {},
    context: @Composable () -> Unit = {},
) {
    ColumnTextText(
        topStr,
        bottomStr,
        modifier = Modifier.clickable(onClick = onClick)
            .padding(top = paddingTop, bottom = paddingBottom, start = 20.dp, end = 20.dp),
        context = context
    )
}