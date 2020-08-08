package com.fon.knjizararest.repository

import com.fon.knjizararest.entity.Author
import com.fon.knjizararest.entity.Book
import com.fon.knjizararest.entity.Comment
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository

@Repository
interface BookRepository:PagingAndSortingRepository<Book,Long> {
    fun findBooksByBookNameContainingOrISBNEquals(bookName: String, ISBN: String, pageable: Pageable):Page<Book>
    fun findBooksByAuthors(author: Author, pageable: Pageable): Page<Book>
    fun existsBookByBookNameOrISBN(bookName:String,ISBN:String):Boolean
    fun findBookByCommentsIn(comments:List<Comment>):Book
}