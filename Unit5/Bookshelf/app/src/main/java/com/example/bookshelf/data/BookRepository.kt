package com.example.bookshelf.data

import com.example.bookshelf.model.Book
import com.example.bookshelf.network.BookImageService

interface BookRepository {
    suspend fun searchBooks(query: String): List<Book>
}

class NetworkBookRepository(
    private val bookImageService: BookImageService
) : BookRepository {
    override suspend fun searchBooks(query: String): List<Book> {
        val response = bookImageService.searchBooks(query)
        if (response.isSuccessful) {
            val items = response.body()?.items ?: emptyList()
            return items.map {
                val imageLinks = it.volumeInfo.imageLinks
                var thumbnailUrl = imageLinks?.thumbnail ?: imageLinks?.smallThumbnail
                // Chuyển link http sang https nếu cần
                if (thumbnailUrl != null && thumbnailUrl.startsWith("http://")) {
                    thumbnailUrl = thumbnailUrl.replaceFirst("http://", "https://")
                }
                Book(
                    id = it.id,
                    title = it.volumeInfo.title ?: "",
                    authors = it.volumeInfo.authors,
                    thumbnail = thumbnailUrl
                )
            }
        }
        return emptyList()
    }
}