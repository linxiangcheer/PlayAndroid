package com.linx.playAndroid.viewModel

import androidx.compose.runtime.mutableStateOf
import com.linx.common.base.BaseViewModel

/**
 * 广场
 */
class SquareViewModel: BaseViewModel() {

    //指示器index
    val squareTopBarIndex = mutableStateOf(0)

}