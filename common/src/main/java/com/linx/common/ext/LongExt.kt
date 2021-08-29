package com.linx.common.ext

import java.text.SimpleDateFormat
import java.util.*

/**
 * Long类型的时间转Date
 * [type] 转换出来的日期格式
 */
fun Long.transitionDate(type: String = "yyyy-MM-dd HH:mm:ss"): String = SimpleDateFormat(type).format(
    Date(this)
)