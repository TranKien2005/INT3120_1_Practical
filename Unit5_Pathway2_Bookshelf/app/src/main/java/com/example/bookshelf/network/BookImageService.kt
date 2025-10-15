package com.example.bookshelf.network

import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.Response
import com.google.gson.annotations.SerializedName

// Data classes cho response
import com.example.bookshelf.model.Book

data class VolumeInfo(
    val title: String?,
    val authors: List<String>?,
    val imageLinks: ImageLinks?
)

data class ImageLinks(
    @SerializedName("thumbnail") val thumbnail: String?,
    @SerializedName("smallThumbnail") val smallThumbnail: String?
)

data class BookItem(
    val id: String,
    val volumeInfo: VolumeInfo
)

data class BooksResponse(
    val items: List<BookItem>?
)

interface BookImageService {
    @GET("volumes")
    suspend fun searchBooks(@Query("q") query: String): Response<BooksResponse>
}