package com.linx.common.ext

import kotlinx.coroutines.Job

/**
 * 添加Job到list中
 */
fun Job.addTo(list: MutableList<Job>) {
    list.add(this)
}