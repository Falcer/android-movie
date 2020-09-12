package dev.falcer.moviedb.data.response


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import dev.falcer.moviedb.data.model.Movie

@JsonClass(generateAdapter = true)
data class UpComingResponse(
    @Json(name = "page")
    var page: Int? = 0,
    @Json(name = "results")
    var results: List<Movie>? = listOf(),
    @Json(name = "total_pages")
    var totalPages: Int? = 0,
    @Json(name = "total_results")
    var totalResults: Int? = 0
)