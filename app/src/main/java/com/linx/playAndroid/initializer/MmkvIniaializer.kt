package com.linx.playAndroid.initializer

import android.content.Context
import android.util.Log
import androidx.startup.Initializer
import com.tencent.mmkv.MMKV

/**
 * 初始化MMKV
 */
class MmkvIniaializer: Initializer<String> {

    override fun create(context: Context): String {
        Log.d("初始化", "初始化MMKV")
        val rootDir: String = MMKV.initialize(context)
        Log.d("初始化", "MMKV根目录：$rootDir")
        return rootDir
    }

    //是否需要在？之后初始化
    override fun dependencies(): List<Class<out Initializer<*>>> {
        return emptyList()
    }
}