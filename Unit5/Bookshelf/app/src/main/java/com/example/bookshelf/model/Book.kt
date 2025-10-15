package com.example.bookshelf.model

data class Book(
    val id: String,
    val title: String,
    val authors: List<String>?,
    val thumbnail: String?
)
