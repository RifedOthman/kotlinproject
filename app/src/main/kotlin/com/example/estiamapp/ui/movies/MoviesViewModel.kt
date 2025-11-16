package com.example.estiamapp.ui.movies

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.estiamapp.data.MovieRepository
import com.example.estiamapp.data.model.MovieDto
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

data class MoviesUiState(
    val all: List<MovieDto> = emptyList(),
    val visibleCount: Int = 0,
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val error: String? = null
) {
    val isEmpty: Boolean
        get() = !isLoading && !isRefreshing && error == null && all.isEmpty()
}

class MoviesViewModel(
    private val repo: MovieRepository = MovieRepository()
): ViewModel()  {
    companion object { private const val CHUNK = 5 }

    var state = mutableStateOf(MoviesUiState())
        private set

    private var loadJob: Job? = null

    init { load() }

    fun load() {
        loadJob?.cancel()
        state.value = state.value.copy(isLoading = true, error = null)
        loadJob = viewModelScope.launch {
            try {
                val movies = repo.fetchPopularMovies()
                state.value = MoviesUiState(
                    all = movies,
                    visibleCount = minOf(CHUNK, movies.size),
                    isLoading = false
                )
            } catch (e: Exception) {
                state.value = state.value.copy(isLoading = false, error = e.message ?: "Error")
            }
        }
    }

    fun refresh() {
        state.value = state.value.copy(isRefreshing = true, error = null)
        viewModelScope.launch {
            try {
                val movies = repo.fetchPopularMovies()
                state.value = MoviesUiState(
                    all = movies,
                    visibleCount = minOf(CHUNK, movies.size),
                    isRefreshing = false
                )
            } catch (e: Exception) {
                state.value = state.value.copy(isRefreshing = false, error = e.message ?: "Error")
            }
        }
    }

    fun loadMore() {
        val s = state.value
        if (s.isLoading || s.isRefreshing) return
        if (s.visibleCount >= s.all.size) return
        state.value = s.copy(visibleCount = minOf(s.visibleCount + CHUNK, s.all.size))
    }
}

