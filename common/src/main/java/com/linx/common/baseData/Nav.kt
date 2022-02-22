package com.linx.common.baseData

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.MutableLiveData
import com.linx.common.R

/**
 * 导航相关
 */
object Nav {

    //密封类关联目的地路线和字符串资源
    sealed class BottomNavScreen(val route: String, @StringRes val resourceId: Int, @DrawableRes val id: Int) {
        object HomeScreen: BottomNavScreen("home", R.string.bottom_home, R.mipmap.nav_home)
        object ProjectScreen: BottomNavScreen("project", R.string.bottom_project, R.mipmap.nav_project)
        object SquareScreen: BottomNavScreen("square", R.string.bottom_square, R.mipmap.nav_square)
        object PublicNumScreen: BottomNavScreen("publicNum", R.string.bottom_public_num, R.mipmap.nav_public_num)
        object LearnScreen: BottomNavScreen("learn", R.string.bottom_learn, R.mipmap.nav_learn)
        object MineScreen: BottomNavScreen("mine", R.string.bottom_mine, R.mipmap.nav_mine)
    }

    //记录BottomNav当前的Item
    val bottomNavRoute = mutableStateOf<BottomNavScreen>(BottomNavScreen.HomeScreen)

    //是否点击两次返回的activity
    var twoBackFinishActivity = false

    //项目页面指示器index
    val projectTopBarIndex = mutableStateOf(0)

    //广场页面指示器index
    val squareTopBarIndex = mutableStateOf(0)

    //公众号页面指示器index
    val publicNumIndex = mutableStateOf(0)

}