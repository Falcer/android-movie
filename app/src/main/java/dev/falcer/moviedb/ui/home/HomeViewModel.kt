package dev.falcer.moviedb.ui.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.falcer.moviedb.datasource.repository.MovieRepository
import dev.falcer.moviedb.utils.NetworkState
import kotlinx.coroutines.launch

class HomeViewModel @ViewModelInject constructor(private val movieRepository: MovieRepository) : ViewModel() {

    fun getUpComingMovies() : MutableLiveData<NetworkState> {
        val networkState = MutableLiveData(NetworkState.loading(true))
        viewModelScope.launch {
            try {
                val result = movieRepository.getUpComingMovies().results
                println("ARGA" + result)
                networkState.value = NetworkState.success(result)
            } catch (e: Exception) {
                networkState.value = NetworkState.error(e)
            }
        }
        return networkState
    }
}