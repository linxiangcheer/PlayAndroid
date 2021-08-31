package com.linx.net.ext

import android.util.Log
import com.linx.net.model.DataResult
import retrofit2.Call
import retrofit2.await

/**
 * 扩展retrofit的返回数据，调用await，并catch超时等异常
 * @receiver Call<T>
 * @return DataResult<T> 返回格式为ApiResponse封装
 */
suspend fun <T : Any> Call<T>.serverData(): DataResult<T> {
    var result: DataResult<T> = DataResult.Loading
    kotlin.runCatching {
        this.await()
    }.onFailure {
        result = DataResult.Error(RuntimeException(it))
    }.onSuccess {
        result = DataResult.Success(it)
    }
    return result
}