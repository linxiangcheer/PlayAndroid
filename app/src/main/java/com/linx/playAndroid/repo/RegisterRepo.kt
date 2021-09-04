package com.linx.playAndroid.repo

import com.linx.net.base.ServiceCreator
import com.linx.playAndroid.service.RegisterService

object RegisterRepo {

    /**
     * 注册
     */
    fun getUserRegister(username: String, password: String, repassword: String) =
        ServiceCreator.getService<RegisterService>().getUserRegister(username, password, repassword)

}