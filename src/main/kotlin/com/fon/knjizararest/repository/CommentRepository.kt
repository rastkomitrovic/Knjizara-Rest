package com.fon.knjizararest.repository

import com.fon.knjizararest.entity.Comment
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface CommentRepository :CrudRepository<Comment,Long>{
    fun findCommentsByBookBookId(bookId: Long): List<Comment>

    @Query("select c from Comment as c where c.user.username=:username and c.book.bookId=:bookId")
    fun existsCommentByUser(username: String, bookId: Long): Boolean
}