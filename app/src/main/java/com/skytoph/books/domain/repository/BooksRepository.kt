package com.skytoph.books.domain.repository

import com.skytoph.books.domain.model.Book
import com.skytoph.books.domain.model.BookCategory

interface BooksRepository {
    suspend fun getCategories(): List<BookCategory>
    suspend fun getBooks(categoryId: Int): List<Book>
}