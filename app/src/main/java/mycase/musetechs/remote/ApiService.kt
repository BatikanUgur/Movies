package mycase.musetechs.remote


import mycase.musetechs.model.MovieResponse
import mycase.musetechs.model.Token
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("/3/authentication/token/new")
    fun getToken(@Query("api_key") apiKey:String):Call<Token>

    @GET("list/1")
    fun getMovies(@Query("api_key") apiKey:String,@Query("page") page:Int):Call<MovieResponse>

}