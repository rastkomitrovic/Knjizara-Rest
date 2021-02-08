package com.fon.knjizararest.service

import com.fon.knjizararest.entity.Comment
import java.util.*

interface CommentService {
    fun findCommentByCommentId(commentId: Long): Optional<Comment> //default method
    fun saveComment(comment: Comment) //default method
    fun findCommentsByBookBookId(bookId: Long): List<Comment>
    fun deleteCommentByCommentId(commentId: Long)
    fun existsCommentByUser(username: String, bookId: Long): Boolean
}