package dev.falcer.moviedb.utils

sealed class NetworkState {
    data class Loading(var loading: Boolean) : NetworkState()
    data class Success(var data: Any?) : NetworkState()
    data class Error(var err: Throwable) : NetworkState()
    companion object {
        fun loading(isLoading: Boolean): NetworkState = Loading(isLoading)
        fun error(e: Throwable): NetworkState = Error(e)
        fun success(data: Any?) : NetworkState = Success(data)
    }
}