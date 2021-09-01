package com.linx.net.base

import com.linx.net.cookie.LocalCookieJar
import com.linx.net.widget.KtHttpLogInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * retrofit2请求
 */
object ServiceCreator {

    private val mOkClient: OkHttpClient = OkHttpClient.Builder()
        //完整请求超时时长，从发起到接受返回数据，默认0s
        .callTimeout(10, TimeUnit.SECONDS)
        //与服务器建立链接的时长，默认10s
        .connectTimeout(10, TimeUnit.SECONDS)
        //读取服务器返回数据的时长
        .readTimeout(10, TimeUnit.SECONDS)
        //向服务器写入数据的时长，默认10s
        .writeTimeout(10, TimeUnit.SECONDS)
        //是否重连
        .retryOnConnectionFailure(true)
        //是否重定向
        .followRedirects(false)
        //cookie持久化处理
        .cookieJar(LocalCookieJar())
        //添加网络拦截器，打印日志
        .addNetworkInterceptor(KtHttpLogInterceptor {
            logLevel(KtHttpLogInterceptor.LogLevel.BODY)
        })
        .build()

    private var retrofit: Retrofit? = null

    private val retrofitBuilder = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .client(mOkClient)

    /**
     * 初始化配置
     */
    fun initConfig(baseUrl: String, okClient: OkHttpClient = mOkClient): ServiceCreator {
        retrofit = retrofitBuilder.baseUrl(baseUrl).client(mOkClient).build()
        return this
    }

    //inline内联修饰符可以使用reified来修饰函数的泛型，让泛型具体化
    inline fun <reified T> getService(): T = create(T::class.java)

    /**
     * 获取retrofit的service对象
     */
    fun <T> create(serviceClazz: Class<T>): T {
        if (retrofit == null) {
            throw UninitializedPropertyAccessException("Retrofit必须初始化，需要配置baseUrl")
        } else {
            return retrofit!!.create(serviceClazz)
        }
    }

}