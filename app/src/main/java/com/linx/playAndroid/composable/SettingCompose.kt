package com.linx.playAndroid.composable

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.linx.common.baseData.CommonConstant
import com.linx.common.baseData.themeColorList
import com.linx.common.baseData.themeTypeState
import com.linx.common.ext.toast
import com.linx.common.model.ThemeType
import com.linx.common.widget.SpUtilsMMKV
import com.linx.net.widget.DataStoreUtils
import com.linx.playAndroid.R
import com.linx.playAndroid.public.*
import com.linx.playAndroid.ui.theme.CustomThemeManager
import com.linx.playAndroid.viewModel.SettingViewModel
import com.linx.playAndroid.widget.CacheDataManager
import com.linx.playAndroid.widget.SmallUtil

/**
 * 设置页面
 */
@Composable
fun SettingCompose(navHostController: NavHostController) {

    val settingViewModel: SettingViewModel = viewModel()

    val content = LocalContext.current

    BaseScreen {
        Scaffold(topBar = {
            val lottieComposition by rememberLottieComposition(
                spec = LottieCompositionSpec.RawRes(
                    R.raw.loader
                )
            )
            AppBar("设置", leftIcon = Icons.Default.ArrowBack, onLeftClick = {
                navHostController.navigateUp()
            }, rightLottie = lottieComposition) {
                "哟，你发现了啥？Lottie动画！".toast(content)
            }
        },
            content = { paddingValues: PaddingValues ->
                SettingCenterScreen(navHostController, settingViewModel)
            }
        )
    }

}

/**
 * 中间的内容布局
 */
@Composable
private fun SettingCenterScreen(
    navHostController: NavHostController,
    settingViewModel: SettingViewModel
) {

    val context = LocalContext.current

    //是否显示首页置顶文章
    var showTopArticle by remember { mutableStateOf(SpUtilsMMKV.getBoolean(CommonConstant.GONE_ARTICLE_TOP) != true) }

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
            navHostController.navigateUp()
        }) { exitLoginState = false }
    }

    //主题颜色弹窗
    var themeColorState by remember { mutableStateOf(false) }
    if (themeColorState) {
        ContentCustomAlertDialog("主题颜色设置", textCompose = {
            ThemeSelectedScreen()
        }) { themeColorState = false }
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        item {
            TopText("基本设置")

            ColumnTextTextScreen("显示置顶文章", "开启后首页会显示置顶文章", onClick = {
                showTopArticle = !showTopArticle
                when (showTopArticle) {
                    //显示
                    true -> SpUtilsMMKV.removeKey(CommonConstant.GONE_ARTICLE_TOP)
                    //隐藏
                    false -> SpUtilsMMKV.put(CommonConstant.GONE_ARTICLE_TOP, true)
                }
            }) {
                Checkbox(
                    showTopArticle,
                    onCheckedChange = null,
                    modifier = Modifier.size(6.dp),
                    colors = CheckboxDefaults.colors(checkedColor = MaterialTheme.colors.primary)
                )
            }

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
            ColumnTextTextScreen("主题颜色", "设置App主题颜色", context = {
                Surface(
                    modifier = Modifier.size(30.dp),
                    shape = CircleShape,
                    color = MaterialTheme.colors.primary,
                    content = {}
                )
            }, onClick = {
                themeColorState = true
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
 * 主题选择布局
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun ThemeSelectedScreen() {
    //aspectRatio 宽高1:1
    val modifier = Modifier.aspectRatio(1f).padding(4.dp)
    //垂直GridList
    LazyVerticalGrid(
        //每行的数量
        cells = GridCells.Fixed(4)
    ) {
        itemsIndexed(themeColorList) { index: Int, theme: ThemeType ->
            Surface(
                border = BorderStroke(1.dp, MaterialTheme.colors.secondaryVariant),
                modifier = modifier.clickable(onClick = {
                    //保存主题颜色
                    SpUtilsMMKV.put(CommonConstant.THEME_COLOR, index)
                    themeTypeState.value = theme
                }),
                shape = CircleShape,
                color = CustomThemeManager.getThemeColor(theme).primary,
            ) {
                //如果是当前选中的主题
                if (themeTypeState.value == theme) {
                    Box(
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            Icons.Default.Lock,
                            contentDescription = null,
                            tint = MaterialTheme.colors.background
                        )
                    }
                }
            }
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