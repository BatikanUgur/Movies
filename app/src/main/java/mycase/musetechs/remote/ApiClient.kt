package mycase.musetechs.remote

import atlasyazilim.nto.biletplaza.util.Constants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object ApiClient {
    fun getApiService(token: String?): ApiService {
        val retrofitBuilder = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(getOkHttpClient(token))
            .build()
        return retrofitBuilder.create(ApiService::class.java)
    }
    private fun getOkHttpClient(token: String?):OkHttpClient{
        val client =OkHttpClient.Builder()
        client.addInterceptor(RequestInterceptor(token))
        return client.build()
    }
}