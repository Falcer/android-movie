package dev.falcer.moviedb.utils

import java.lang.Exception

sealed class NetworkState

object Loading : NetworkState()
data class Success(var result: Any?) : NetworkState()
data class Error(var err: Exception) : NetworkState()