package com.example.estiamapp.data.remote

import com.example.estiamapp.data.model.MovieDto

data class MoviesResponse(
    val results: List<MovieDto>,
    val page: Int? = null,
    val total_pages: Int? = null,
    val total_results: Int? = null
)

