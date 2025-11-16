package com.example.estiamapp.data.remote

import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface ApiService {
    @GET
    suspend fun getUsers(@Url url: String = "https://dummyjson.com/users"): UsersResponse

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String = ApiConfig.TMDB_API_KEY,
        @Query("language") language: String = ApiConfig.TMDB_LANGUAGE
    ): MoviesResponse
    
    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(
        @Query("api_key") apiKey: String = ApiConfig.TMDB_API_KEY,
        @Query("language") language: String = ApiConfig.TMDB_LANGUAGE
    ): MoviesResponse
    
    @GET
    suspend fun getProducts(@Url url: String = "https://dummyjson.com/products"): ProductsResponse
}