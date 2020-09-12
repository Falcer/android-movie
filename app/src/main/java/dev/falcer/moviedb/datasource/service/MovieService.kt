package dev.falcer.moviedb.datasource.service

import dev.falcer.moviedb.data.response.UpComingResponse
import retrofit2.Call
import retrofit2.http.GET

interface MovieService {
    @GET("movie/upcoming")
    suspend fun getUpComingMovies() : UpComingResponse

//    @GET("movie/popular")
//    fun getPopularMovie()  : Call<PopularRespose>
}