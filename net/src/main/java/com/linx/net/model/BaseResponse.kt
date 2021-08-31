package com.linx.net.model

import androidx.annotation.Keep

/**
 * 服务器返回的数据基类
 */
@Keep
data class BaseResponse(

    //接口数据
    val data: Any?,

    //接口状态码
    val errorCode: Int,

    //接口出错消息
    val errorMsg: String = ""
) {
    companion object {
        const val SERVER_CODE_SUCCESS = 0 //接口请求响应业务处理 成功
    }
}