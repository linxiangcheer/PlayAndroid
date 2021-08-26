package com.linx.common.baseData

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.MutableLiveData
import com.linx.common.model.StatusBarTitleData
import com.linx.common.widget.SingleLiveEvent

/**
 * 主界面标题栏
 */
val statusBarTitle: MutableState<StatusBarTitleData> by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
    mutableStateOf<StatusBarTitleData>(StatusBarTitleData(""))
}