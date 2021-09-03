package com.linx.playAndroid.repo

import com.linx.net.base.ServiceCreator
import com.linx.playAndroid.service.LoginService
import retrofit2.http.Body

object LoginRepo {

    /**
     * 登录
     */
    fun getUserLogin(username: String, password: String) =
        ServiceCreator.getService<LoginService>().getUserLogin(username, password)

}