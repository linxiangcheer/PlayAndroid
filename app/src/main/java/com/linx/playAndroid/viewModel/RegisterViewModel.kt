package com.linx.playAndroid.viewModel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.linx.common.base.BaseViewModel
import com.linx.net.ext.*
import com.linx.playAndroid.model.RegisterDaat
import com.linx.playAndroid.repo.RegisterRepo

/**
 * 注册
 */
class RegisterViewModel : BaseViewModel() {

    //用于执行Toast的变量
    val mToast = MutableLiveData<String>()

    //用户名
    val userName = mutableStateOf(TextFieldValue(""))

    //密码
    val passWord = mutableStateOf(TextFieldValue(""))

    //密码是否可见
    val passwordVisible = mutableStateOf(false)

    //注册信息
    private val _userRegisterData = MutableLiveData<RegisterDaat>()
    val userRegisterData: LiveData<RegisterDaat>
        get() = _userRegisterData

    /**
     * 注册
     */
    fun getUserRegisterData() = serverAwait {

        if (userName.value.text == "") {
            mToast.value = "用户名不能为空"
        }

        if (passWord.value.text == "") {
            mToast.value = "密码不能为空"
            return@serverAwait
        }

        RegisterRepo.getUserRegister(userName.value.text, passWord.value.text, passWord.value.text)
            .serverData().onSuccess {
                onBizError { code, message ->
                    mToast.value = "注册失败 $message"
                    Log.e("xxx", "注册 接口异常 $code $message")
                }
                onBizOK<RegisterDaat> { code, data, message ->
                    _userRegisterData.postValue(data)
                }
            }
            .onFailure {
                Log.e("xxx", "注册 接口异常 $it")
            }
    }

}