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
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.google.accompanist.insets.navigationBarsPadding
import com.linx.playAndroid.public.AppBar
import com.linx.playAndroid.public.BaseScreen
import com.linx.playAndroid.viewModel.IntegralRankViewModel

/**
 * 积分排行
 */
@Composable
fun IntegralRankCompose(navController: NavController) {

    val integralRankViewModel: IntegralRankViewModel = viewModel()

    integralRankViewModel.apply {
        getUserInfoIntegral()
    }

    //布局
    IntegralScreen(integralRankViewModel, navController)
}

/**
 * 积分排行布局
 */
@Composable
private fun IntegralScreen(
    integralRankViewModel: IntegralRankViewModel,
    navController: NavController
) {

    BaseScreen {
        Scaffold(
            topBar = {
                AppBar("积分排行", leftIcon = Icons.Default.ArrowBack, onLeftClick = {
                    navController.navigateUp()
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

            }
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
private fun TextCustom(text: String, modifier: Modifier = Modifier, textAlign: TextAlign? = null) {
    Text(
        text, fontWeight = FontWeight.Bold, color = MaterialTheme.colors.primary,
        fontSize = 20.sp,
        modifier = modifier,
        textAlign = textAlign
    )
}