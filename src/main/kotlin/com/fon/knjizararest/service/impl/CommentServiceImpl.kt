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
    }

    override fun findCommentsByBookBookId(bookId: Long): List<Comment> {
        return commentRepository.findCommentsByBookBookId(bookId)
    }

    override fun deleteCommentByCommentId(commentId: Long) {
        commentRepository.deleteById(commentId)
    }

    override fun existsCommentByUser(username: String, bookId: Long): Boolean {
        return commentRepository.existsCommentByUser(username, bookId).isPresent
    }
}