package com.example.estiamapp.ui.movies

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.estiamapp.data.model.MovieDto
import com.example.estiamapp.ui.theme.EstiamAppTheme

@Composable
fun MovieCard(movie: MovieDto, modifier: Modifier = Modifier) {
    Card(modifier = modifier.fillMaxHeight()) {
        Row(Modifier.padding(12.dp)) {
            if (movie.imageUrl.isNotEmpty()) {
                AsyncImage(
                    model = movie.imageUrl,
                    contentDescription = movie.title,
                    modifier = Modifier.size(80.dp)
                )
            }
            Column(Modifier.weight(2f).padding(start = if (movie.imageUrl.isNotEmpty()) 12.dp else 0.dp)) {
                Text(
                    text = movie.title,
                    style = MaterialTheme.typography.titleLarge,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "⭐ ${movie.rating} | ${movie.year}",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(vertical = 4.dp)
                )
                Text(
                    text = movie.fullDescription,
                    style = MaterialTheme.typography.bodySmall,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@Preview(showBackground = true, name = "MovieCard")
@Composable
private fun MovieCardPreview() {
    EstiamAppTheme {
        MovieCard(
            movie = MovieDto(
                id = 1,
                title = "Inception",
                overview = "Un voleur expérimenté qui s'infiltre dans les rêves des autres est chargé de planter une idée dans l'esprit d'un PDG.",
                posterPath = "/9gk7adHYeDvHkCSEqAvQNLV5Uge.jpg",
                releaseDate = "2010-07-16",
                voteAverage = 8.8,
                voteCount = 25000
            )
        )
    }
}

