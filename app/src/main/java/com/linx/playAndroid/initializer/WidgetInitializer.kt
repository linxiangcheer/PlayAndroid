package com.linx.playAndroid.initializer

import android.content.Context
import android.util.Log
import androidx.startup.Initializer
import com.linx.net.widget.DataStoreUtils

/**
 * 初始化工具类
 */
class WidgetInitializer: Initializer<Boolean> {

    override fun create(context: Context): Boolean {
        Log.i("初始化", "初始化DataStore")
        DataStoreUtils.init(context)
        return true
    }

    override fun dependencies(): List<Class<out Initializer<*>>> = emptyList()
}