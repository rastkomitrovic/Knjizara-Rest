package com.fon.knjizararest.repository

import com.fon.knjizararest.entity.Comment
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface CommentRepository :CrudRepository<Comment,Long>{
    fun findCommentsByBookBookId(bookId: Long): List<Comment>
}