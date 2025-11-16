package com.example.estiamapp.data.model

import com.squareup.moshi.Json

data class MovieDto (
    val id: Int,
    val title: String,
    val description: String? = null,
    @Json(name = "overview") val overview: String? = null,
    @Json(name = "poster_path") val posterPath: String? = null,
    @Json(name = "backdrop_path") val backdropPath: String? = null,
    @Json(name = "release_date") val releaseDate: String? = null,
    @Json(name = "vote_average") val voteAverage: Double? = null,
    @Json(name = "vote_count") val voteCount: Int? = null,
    @Json(name = "popularity") val popularity: Double? = null,
    @Json(name = "genre_ids") val genreIds: List<Int>? = null,
    @Json(name = "original_title") val originalTitle: String? = null,
    @Json(name = "original_language") val originalLanguage: String? = null
) {
    val imageUrl: String
        get() = if (!posterPath.isNullOrEmpty()) {
            "https://image.tmdb.org/t/p/w500$posterPath"
        } else {
            ""
        }
    
    val backdropUrl: String
        get() = if (!backdropPath.isNullOrEmpty()) {
            "https://image.tmdb.org/t/p/w1280$backdropPath"
        } else {
            ""
        }
    
    val fullDescription: String
        get() = description ?: overview ?: "Aucune description disponible"
    
    val year: String
        get() = releaseDate?.take(4) ?: "N/A"
    
    val rating: String
        get() = voteAverage?.let { String.format("%.1f", it) } ?: "N/A"
}

