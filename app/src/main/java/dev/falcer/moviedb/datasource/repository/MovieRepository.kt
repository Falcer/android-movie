package dev.falcer.moviedb.datasource.repository

import dev.falcer.moviedb.datasource.service.MovieService
import javax.inject.Inject

class MovieRepository @Inject constructor(private val network : MovieService) {

    suspend fun getUpComingMovies() = network.getUpComingMovies()

}