package com.linx.common.widget

import com.google.gson.Gson
import com.google.gson.GsonBuilder

/**
 * 数据解析帮助类
 */
object GsonUtils {

    private val gsonBuilder: GsonBuilder by lazy { GsonBuilder() }

    val gson: Gson by lazy { gsonBuilder.create() }

    fun toJson(paramObject: Any?): String = gson.toJson(paramObject)

}