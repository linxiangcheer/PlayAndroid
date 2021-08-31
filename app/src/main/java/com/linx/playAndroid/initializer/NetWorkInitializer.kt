package com.linx.playAndroid.initializer

import android.content.Context
import android.util.Log
import androidx.startup.Initializer
import com.linx.net.base.NetUrl
import com.linx.net.base.ServiceCreator

class NetWorkInitializer: Initializer<Boolean> {

    override fun create(context: Context): Boolean {
        Log.d("初始化", "初始化Retrofit2")
        ServiceCreator.initConfig(NetUrl.BASE_URL)
        return true
    }

    override fun dependencies(): List<Class<out Initializer<*>>> {
        return emptyList()
    }

}