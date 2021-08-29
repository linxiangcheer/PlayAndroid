package com.linx.common.ext

import com.linx.common.widget.GsonUtils

/**
 * 一些常用的方法
 */

fun Any?.toJsonStr(): String {
    return GsonUtils.toJson(this)
}


