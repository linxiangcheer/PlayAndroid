package com.linx.playAndroid.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.google.accompanist.insets.navigationBarsPadding
import com.linx.playAndroid.model.CoinRankData
import com.linx.playAndroid.public.AppBar
import com.linx.playAndroid.public.BaseScreen
import com.linx.playAndroid.public.SwipeRefreshContent
import com.linx.playAndroid.viewModel.IntegralRankViewModel

/**
 * 积分排行
 */
@Composable
fun IntegralRankCompose(navHostController: NavHostController) {

    val integralRankViewModel: IntegralRankViewModel = viewModel()

    //积分排行数据
    val coinRankData = integralRankViewModel.coinRankListData.collectAsLazyPagingItems()

    LaunchedEffect(coinRankData.itemCount) {
        if (!integralRankViewModel.isRequestUserInfoData && coinRankData.itemCount > 0) {
            //标记已经请求过个人积分数据
            integralRankViewModel.isRequestUserInfoData = true
            //获取个人积分数据
            integralRankViewModel.getUserInfoIntegral()
        }
    }

    //布局
    IntegralScreen(navHostController, integralRankViewModel, coinRankData)
}

/**
 * 积分排行布局
 */
@Composable
private fun IntegralScreen(
    navHostController: NavHostController,
    integralRankViewModel: IntegralRankViewModel,
    coinRankData: LazyPagingItems<CoinRankData>
) {

    BaseScreen {
        Scaffold(
            topBar = {
                AppBar("积分排行", leftIcon = Icons.Default.ArrowBack, onLeftClick = {
                    navHostController.navigateUp()
                })
            },
            bottomBar = {
                val userInfoIntegralData = integralRankViewModel.userInfoIntegral.observeAsState()
                userInfoIntegralData.value?.let { data ->
                    BottomBarScreen(
                        data.rank ?: "0",
                        data.username ?: "",
                        data.coinCount.toString()
                    )
                }
            },
            content = { paddingValues: PaddingValues ->
                //带刷新头的列表
                SwipeRefreshContent(
                    integralRankViewModel,
                    coinRankData,
                    cardHeight = 65.dp
                ) { index: Int, data: CoinRankData ->
                    CardContent(
                        (index + 1).toString(),
                        data.username ?: "",
                        data.coinCount.toString()
                    )
                }
            }
        )
    }

}

/**
 * 卡片内的内容
 */
@Composable
private fun CardContent(
    //排名
    rank: String,
    //用户名
    userName: String,
    //积分数量
    integralNum: String
) {
    Row(
        modifier = Modifier.padding(start = 20.dp, end = 20.dp).fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically
    ) {

        TextCustom(rank, color = MaterialTheme.colors.secondaryVariant)

        TextCustom(
            userName,
            modifier = Modifier.padding(start = 20.dp),
            fontWeight = FontWeight.Normal,
            color = Color.Gray
        )

        TextCustom(
            integralNum,
            modifier = Modifier.weight(1f, true),
            color = Color.Gray,
            textAlign = TextAlign.End
        )

    }
}

/**
 * 底部栏
 */
@Composable
private fun BottomBarScreen(
    //排名
    rank: String,
    //用户名
    userName: String,
    //积分数量
    integralNum: String
) {
    Surface(
        elevation = 4.dp
    ) {
        Row(
            modifier = Modifier.background(MaterialTheme.colors.background)
                .padding(start = 30.dp, end = 30.dp).height(70.dp).navigationBarsPadding()
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextCustom(rank)
            TextCustom(userName, modifier = Modifier.padding(start = 20.dp))
            TextCustom(
                integralNum, //靠右对齐
                textAlign = TextAlign.End,
                modifier = Modifier.weight(1f, true)
            )
        }
    }
}

/**
 * 文本控件
 */
@Composable
private fun TextCustom(
    text: String,
    modifier: Modifier = Modifier,
    textAlign: TextAlign? = null,
    color: Color = MaterialTheme.colors.primary,
    fontWeight: FontWeight = FontWeight.Bold
) {
    Text(
        text, fontWeight = fontWeight, color = color,
        fontSize = 20.sp,
        modifier = modifier,
        textAlign = textAlign
    )
}