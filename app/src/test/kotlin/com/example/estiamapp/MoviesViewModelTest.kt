package com.example.estiamapp

import com.example.estiamapp.data.MovieRepository
import com.example.estiamapp.data.model.MovieDto
import com.example.estiamapp.ui.movies.MoviesViewModel
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

class MoviesViewModelTest {
    
    @Mock
    private lateinit var mockRepository: MovieRepository
    
    private lateinit var viewModel: MoviesViewModel
    
    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
    }
    
    @Test
    fun `initial state should have empty list`() {
        viewModel = MoviesViewModel(mockRepository)
        val state = viewModel.state.value
        
        assertTrue(state.all.isEmpty())
        assertEquals(0, state.visibleCount)
        assertFalse(state.isLoading)
        assertFalse(state.isRefreshing)
        assertNull(state.error)
    }
    
    @Test
    fun `isEmpty should be true when list is empty and not loading`() {
        val state = com.example.estiamapp.ui.movies.MoviesUiState(
            all = emptyList(),
            visibleCount = 0,
            isLoading = false,
            isRefreshing = false,
            error = null
        )
        
        assertTrue(state.isEmpty)
    }
    
    @Test
    fun `isEmpty should be false when list has items`() {
        val movies = listOf(
            MovieDto(id = 1, title = "Test Movie", overview = "", posterPath = "")
        )
        val state = com.example.estiamapp.ui.movies.MoviesUiState(
            all = movies,
            visibleCount = 1,
            isLoading = false,
            isRefreshing = false,
            error = null
        )
        
        assertFalse(state.isEmpty)
    }
    
    @Test
    fun `isEmpty should be false when loading`() {
        val state = com.example.estiamapp.ui.movies.MoviesUiState(
            all = emptyList(),
            visibleCount = 0,
            isLoading = true,
            isRefreshing = false,
            error = null
        )
        
        assertFalse(state.isEmpty)
    }
    
    @Test
    fun `loadMore should increment visibleCount when not at end`() {
        viewModel = MoviesViewModel(mockRepository)
        val movies = (1..10).map { 
            MovieDto(id = it, title = "Movie $it", overview = "", posterPath = "")
        }
        
        // Simulate loaded state
        viewModel.state.value = com.example.estiamapp.ui.movies.MoviesUiState(
            all = movies,
            visibleCount = 5,
            isLoading = false,
            isRefreshing = false,
            error = null
        )
        
        viewModel.loadMore()
        
        assertEquals(10, viewModel.state.value.visibleCount) // Should be min(5+5, 10) = 10
    }
    
    @Test
    fun `loadMore should not increment when at end`() {
        viewModel = MoviesViewModel(mockRepository)
        val movies = (1..5).map { 
            MovieDto(id = it, title = "Movie $it", overview = "", posterPath = "")
        }
        
        viewModel.state.value = com.example.estiamapp.ui.movies.MoviesUiState(
            all = movies,
            visibleCount = 5,
            isLoading = false,
            isRefreshing = false,
            error = null
        )
        
        viewModel.loadMore()
        
        assertEquals(5, viewModel.state.value.visibleCount) // Should not change
    }
}

