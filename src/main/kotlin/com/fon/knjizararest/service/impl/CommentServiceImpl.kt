package com.fon.knjizararest.service.impl

import com.fon.knjizararest.entity.Comment
import com.fon.knjizararest.repository.BookRepository
import com.fon.knjizararest.repository.CommentRepository
import com.fon.knjizararest.service.CommentService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class CommentServiceImpl(@Autowired val commentRepository: CommentRepository , @Autowired val bookRepository: BookRepository) : CommentService {
    override fun findCommentByCommentId(commentId: Long): Optional<Comment> {
        return commentRepository.findById(commentId)
    }

    override fun saveComment(comment: Comment) {
        commentRepository.save(comment)
        val book=bookRepository.findBookByComments(listOf(commentRepository.findById(commentId).get()))
        var sum=0f;
        book.comments.forEach {
            sum+=it.rating
        }
        book.rating=sum/(book.comments.size)
        bookRepository.save(book)
    }

    override fun findCommentsByBookBookId(bookId: Long): List<Comment> {
        return commentRepository.findCommentsByBookBookId(bookId)
    }

    override fun deleteCommentByCommentId(commentId: Long) {
        val book=bookRepository.findBookByComments(listOf(commentRepository.findById(commentId).get()))
        var sum=0f;
        book.comments.forEach {
            if(it.commentId!=commentId)
                sum+=it.rating
        }
        book.rating=sum/(book.comments.size - 1)
        bookRepository.save(book)
        commentRepository.deleteById(commentId)
    }
}