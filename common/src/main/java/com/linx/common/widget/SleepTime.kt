package com.linx.common.widget

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * 开启一个IO线程，一段时间后执行代码
 */
@Composable
fun sleepTime(millis: Long = 1500, block: () -> Unit) {
    LaunchedEffect(Unit) {
        launch(Dispatchers.IO) {
            Thread.sleep(millis)
            block()
        }
    }
}

/**
 * 开启一个IO线程，一段时间后执行代码
 */
fun ViewModel.sleepTime(millis: Long = 1500, block: () -> Unit) {
    viewModelScope.launch(Dispatchers.IO) {
        Thread.sleep(millis)
        block()
    }
}