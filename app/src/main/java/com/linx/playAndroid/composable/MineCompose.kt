package com.linx.playAndroid.composable

import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.linx.common.baseData.refreshUserMessageData
import com.linx.common.ext.joinQQGroup
import com.linx.common.ext.toast
import com.linx.playAndroid.KeyNavigationRoute
import com.linx.playAndroid.R
import com.linx.playAndroid.viewModel.MineViewModel

/**
 * 我的页面
 */
@Composable
fun MineCompose(navHostController: NavHostController) {

    val mineViewModel: MineViewModel = viewModel()

    mineViewModel.apply {
        //如果登录了就个人积分数据
        if (isLogin()) {
            //监听 刷新个人信息数据
            LaunchedEffect(refreshUserMessageData.value) {
                //获取个人积分数据
                getUserInfoIntegral()
            }
        } else if (userInfoIntegral.value != null)
            //如果退出登录的话会走这里
            userInfoIntegral.value = null
    }

    //页面布局
    MineScreen(mineViewModel, navHostController)

}

/**
 * 页面布局
 */
@Composable
private fun MineScreen(
    mineViewModel: MineViewModel,
    navHostController: NavHostController
) {
    //个人积分数据
    val userInfoIntegralData = mineViewModel.userInfoIntegral.observeAsState()

    Column(
        modifier = Modifier.background(MaterialTheme.colors.primary).fillMaxSize()
    ) {

        userInfoIntegralData.value.apply {
            //头像和名字
            HeadAndName(
                this?.username ?: "请先登录~",
                if (this?.userId == 0 || this?.userId == null) "" else this.userId.toString(),
                if (this?.rank == null) "" else rank.toString()
            ) {
                //未登录才跳转到登录页面
                if (!mineViewModel.isLogin()) {
                    //跳转到登录页面
                    navHostController.navigate(KeyNavigationRoute.LOGIN.route)
                }
            }
        }

        val context = LocalContext.current

        //下方列表
        Surface(
            shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
            color = MaterialTheme.colors.background,
            modifier = Modifier.fillMaxSize().padding(top = 50.dp)
        ) {
            Column(modifier = Modifier.padding(top = 13.dp).fillMaxSize()) {
                MineListComposable(
                    painterResource(R.mipmap.ic_jifen),
                    "我的积分",
                    userInfoIntegralData.value?.coinCount ?: 0
                ) {
                    if (mineViewModel.isLogin())
                    //跳转到积分排行页面
                        navHostController.navigate(
                            KeyNavigationRoute.INTEGRAL_RANK.route
                        )
                    else {
                        "请先登录".toast(context)
                    }
                }
                MineListComposable(painterResource(R.mipmap.ic_collect), "我的收藏") {
                    if (mineViewModel.isLogin())
                    //跳转到我的收藏页面
                        navHostController.navigate(
                            KeyNavigationRoute.MY_COLLECT.route
                        )
                    else {
                        "请先登录".toast(context)
                    }
                }
                MineListComposable(painterResource(R.mipmap.ic_wenzhang), "我的文章") {
                    if (mineViewModel.isLogin())
                    //跳转到我的文章页面
                        navHostController.navigate(
                            KeyNavigationRoute.MY_SHARE_ARTICLES.route
                        )
                    else {
                        "请先登录".toast(context)
                    }
                }
                MineListComposable(painterResource(R.mipmap.ic_web), "开源网站") {
                    navHostController.navigate("${KeyNavigationRoute.WEBVIEW.route}?url=https://www.wanandroid.com")
                }
                MineListComposable(painterResource(R.mipmap.ic_jairu), "加入我们") {
                    joinQQGroup(context, "jtDR37meSA0N3ceEPYRk3IbaqkqtsQgm")
                }
                MineListComposable(painterResource(R.mipmap.ic_shezhi), "系统设置") {
                    //跳转到设置页面
                    navHostController.navigate(
                        KeyNavigationRoute.SETTING.route
                    )
                }
            }
        }

    }
}

/**
 * 列表
 */
@Composable
private fun MineListComposable(
    painter: Painter,
    leftStr: String,
    //积分
    integral: Int = -1,
    onClick: () -> Unit
) {

    Row(
        modifier = Modifier.background(MaterialTheme.colors.background)
            .clickable(
                onClick = onClick,
                interactionSource = MutableInteractionSource(),
                indication = null
            )
            .padding(start = 20.dp, end = 20.dp, top = 13.dp, bottom = 13.dp)
            .height(26.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Image(
            painter,
            contentDescription = null,
            modifier = Modifier.size(26.dp)
        )

        Text(
            leftStr,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colors.secondaryVariant,
            fontSize = 15.sp,
            modifier = Modifier.padding(start = 14.dp)
        )

        Spacer(modifier = Modifier.weight(1f, true))

        if (integral != -1) {
            Text(
                "当前积分：",
                color = Color.Gray,
                fontWeight = FontWeight.Light,
                fontSize = 13.sp
            )

            Text(
                integral.toString(),
                color = MaterialTheme.colors.primary,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
        }

        //箭头
        Icon(
            painterResource(R.mipmap.ic_right),
            contentDescription = null,
            modifier = Modifier.padding(start = 8.dp).size(16.dp),
            tint = MaterialTheme.colors.secondaryVariant
        )

    }
}

/**
 * 头像和名字
 */
@Composable
private fun HeadAndName(name: String, id: String, rank: String, goLogin: () -> Unit) {
    Row(
        modifier = Modifier
            .clickable(
                onClick = goLogin,
                interactionSource = MutableInteractionSource(),
                indication = null
            ).height(80.dp).fillMaxWidth()
            .padding(start = 20.dp)
    ) {
        //头像
        Surface(
            //圆
            shape = CircleShape,
            modifier = Modifier.size(80.dp)
        ) {
            Image(painterResource(R.mipmap.ic_account), contentDescription = null)
        }

        Column(
            modifier = Modifier.padding(start = 20.dp).weight(1f).fillMaxHeight(),
            //平分
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(name, fontWeight = FontWeight.Bold, fontSize = 20.sp)

            Row {
                Text("id: $id", fontSize = 14.sp)
                Text("排名: $rank", modifier = Modifier.padding(start = 20.dp), fontSize = 14.sp)
            }
        }
    }
}