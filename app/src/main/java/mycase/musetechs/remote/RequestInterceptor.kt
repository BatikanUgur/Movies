package mycase.musetechs.remote

import okhttp3.Interceptor
import okhttp3.Response

class RequestInterceptor(token: String?):Interceptor {
    val token = token
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val requestBuilder = originalRequest.newBuilder()
        requestBuilder.addHeader("Content-Type","application/json;charset=utf-8")
        if(token != null) {
            requestBuilder.addHeader("Authorization", "Bearer $token")
        }

        return chain.proceed(requestBuilder.build())
    }
}