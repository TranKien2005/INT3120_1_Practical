package com.example.bookshelf

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.bookshelf.ui.theme.BookshelfTheme
import com.example.bookshelf.data.AppContainer
import com.example.bookshelf.data.DefaultAppContainer
import com.example.bookshelf.model.Book
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private val appContainer: AppContainer = DefaultAppContainer()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BookshelfTheme {
                val viewModel: BookshelfViewModel = viewModel(
                    factory = BookshelfViewModelFactory(appContainer)
                )
                BookshelfApp(viewModel)
            }
        }
    }
}

class BookshelfViewModel(private val appContainer: AppContainer) : ViewModel() {
    var books by mutableStateOf<List<Book>>(emptyList())
        private set
    var isLoading by mutableStateOf(false)
        private set
    var error by mutableStateOf<String?>(null)
        private set

    init {
        searchBooks("android")
    }

    fun searchBooks(query: String) {
        viewModelScope.launch {
            isLoading = true
            error = null
            try {
                books = appContainer.bookRepository.searchBooks(query)
            } catch (e: Exception) {
                error = e.message
            }
            isLoading = false
        }
    }
}

class BookshelfViewModelFactory(private val appContainer: AppContainer) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BookshelfViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return BookshelfViewModel(appContainer) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookshelfApp(viewModel: BookshelfViewModel) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { TopAppBar(
            title = { Text("Bookshelf",
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            ) }
        ) }
    ) { padding ->
        if (viewModel.isLoading) {
            Text("Loading...", modifier = Modifier.padding(padding))
        } else if (viewModel.error != null) {
            Text("Error: ${viewModel.error}", modifier = Modifier.padding(padding))
        } else {
            BookGrid(books = viewModel.books, modifier = Modifier.padding(padding))
        }
    }
}

@Composable
fun BookGrid(books: List<Book>, modifier: Modifier = Modifier) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(8.dp)
    ) {
        items(books.size) { index ->
            val book = books[index]
            BookCard(book)
        }
    }
}

@Composable
fun BookCard(book: Book) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(4.dp),
        shape = MaterialTheme.shapes.medium
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            if (!book.thumbnail.isNullOrEmpty()) {
                AsyncImage(
                    model = book.thumbnail,
                    contentDescription = book.title,
                    modifier = Modifier
                        .height(180.dp)
                        .fillMaxWidth(),
                    contentScale = ContentScale.Crop
                )
                Text(
                    text = book.thumbnail ?: "",
                    style = MaterialTheme.typography.bodySmall,
                    maxLines = 1
                )
            } else {
                Box(
                    modifier = Modifier
                        .height(180.dp)
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Text("No Image", style = MaterialTheme.typography.bodySmall)
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = book.title,
                style = MaterialTheme.typography.titleMedium,
                maxLines = 2
            )
            book.authors?.let {
                Text(
                    text = it.joinToString(),
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 1
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BookCardPreview() {
    BookCard(
        book = Book(
            id = "1",
            title = "Sample Book",
            authors = listOf("Author 1", "Author 2"),
            thumbnail = null
        )
    )
}
