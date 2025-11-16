package com.example.estiamapp.data

import com.example.estiamapp.data.model.MovieDto
import com.example.estiamapp.data.remote.ApiClient

class MovieRepository {
    suspend fun fetchPopularMovies(): List<MovieDto> = ApiClient.api.getPopularMovies().results
    suspend fun fetchTopRatedMovies(): List<MovieDto> = ApiClient.api.getTopRatedMovies().results
}

