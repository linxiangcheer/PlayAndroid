package com.linx.playAndroid.viewModel

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.input.TextFieldValue
import com.linx.common.base.BaseViewModel

/**
 * 登录
 */
class LoginViewModel: BaseViewModel() {

    //用户名
    val userName = mutableStateOf(TextFieldValue(""))

    //密码
    val passWord = mutableStateOf(TextFieldValue(""))

    //密码是否可见
    val passwordVisible = mutableStateOf(false)

}