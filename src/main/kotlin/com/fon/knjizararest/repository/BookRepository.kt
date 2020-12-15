package com.fon.knjizararest.repository

import com.fon.knjizararest.entity.Author
import com.fon.knjizararest.entity.Book
import com.fon.knjizararest.entity.Comment
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface BookRepository : PagingAndSortingRepository<Book, Long> {
    @Query("select b from Book as b where b.bookName like CONCAT('%',:param ,'%') or b.publisher.publisherName like CONCAT('%',:param ,'%') or exists (select a from b.authors as a where a.firstName like CONCAT('%',:param ,'%') or a.lastName like CONCAT('%',:param ,'%') or a.middleName like CONCAT('%',:param ,'%'))")
    fun findBooksSearch(@Param("param") param: String, pageable: Pageable): Page<Book>

    @Query("select b from Book as b where exists (select a from b.authors as a where a.authorId=:authorId)")
    fun findBooksByAuthors(authorId: Long, pageable: Pageable): Page<Book>

    @Query("select b from Book as b where b.publisher.publisherId=:publisherId")
    fun findBooksByPublisher(publisherId: Long, pageable: Pageable): Page<Book>

    @Query("select b from Book as b where exists (select g from b.genres as g where g.genreId=:genreId)")
    fun findBooksByGenre(genreId: Long, pageable: Pageable): Page<Book>

    @Query("select b from Book as b where b.ISBN=:ISBN")
    fun existsBookISBN(ISBN: String): Optional<Book>
}