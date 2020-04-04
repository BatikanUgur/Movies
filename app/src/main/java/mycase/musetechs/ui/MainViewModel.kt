package mycase.musetechs.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import atlasyazilim.nto.biletplaza.util.Constants
import mycase.musetechs.model.MovieResponse
import mycase.musetechs.model.Token
import mycase.musetechs.remote.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel(){

    val tokenLiveData : MutableLiveData<Token> = MutableLiveData()
    val moviesLiveData : MutableLiveData<MovieResponse> = MutableLiveData()

    fun getToken(){
        ApiClient.getApiService(null).getToken(Constants.API_KEY).enqueue(object : Callback<Token>{
            override fun onFailure(call: Call<Token>, t: Throwable) {
                tokenLiveData.postValue(null)
            }

            override fun onResponse(call: Call<Token>, response: Response<Token>) {
                tokenLiveData.postValue(response.body())
            }

        })
    }

    fun getMovies(token:String,page:Int){
        ApiClient.getApiService(token).getMovies(Constants.API_KEY,page).enqueue(object : Callback<MovieResponse>{
            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                moviesLiveData.postValue(null)
            }

            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                moviesLiveData.postValue(response.body())
            }

        })
    }

}