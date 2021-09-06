package com.linx.playAndroid.widget

import android.content.Context
import android.content.pm.PackageManager

object SmallUtil {

    /**
     * 获取版本号
     * */
    fun packageCode(context: Context): String {
        val manager = context.packageManager
        var code = ""
        try {
            val info = manager.getPackageInfo(context.packageName, 0)
            code = info.versionName
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }
        return code
    }

}