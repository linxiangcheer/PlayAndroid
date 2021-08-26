package com.linx.common.baseData

import androidx.annotation.StringRes
import com.linx.common.R

/**
 * 导航相关
 */
object Nav {

    //密封类关联目的地路线和字符串资源
    sealed class BottomNavScreen(val route: String, @StringRes val resourceId: Int) {
        object HomeScreen : BottomNavScreen("home", R.string.bottom_home)
        object ProjectScreen : BottomNavScreen("project", R.string.bottom_project)
    }

    //记录BottomNav当前的Route
    var bottomNavRoute: String = BottomNavScreen.HomeScreen.route

}