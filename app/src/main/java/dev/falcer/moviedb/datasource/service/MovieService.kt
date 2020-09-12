package dev.falcer.moviedb.datasource.service

import dev.falcer.moviedb.data.response.UpComingResponse
import retrofit2.Call
import retrofit2.http.GET

interface MovieService {
    @GET("movie/upcoming")
    fun getUpComingMovies() : Call<UpComingResponse>

//    @GET("movie/popular")
//    fun getPopularMovie()  : Call<PopularRespose>
}