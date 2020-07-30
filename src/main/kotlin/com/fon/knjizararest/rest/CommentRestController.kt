package com.fon.knjizararest.rest

import com.fon.knjizararest.entity.Comment
import com.fon.knjizararest.service.CommentService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v0/comments")
class CommentRestController(@Autowired val commentService: CommentService) {

    @GetMapping("/{bookId}", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun findCommentsByBookId(@PathVariable bookId: Long): ResponseEntity<List<Comment>> {
        val comments = commentService.findCommentsByBookBookId(bookId)
        return when (comments.isNotEmpty()) {
            true -> ResponseEntity(comments, HttpStatus.OK)
            else -> ResponseEntity(HttpStatus.NO_CONTENT)
        }
    }

    @PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun saveComment(comment: Comment): ResponseEntity<Any> {
        return when (commentService.findCommentByCommentId(comment.commentId).isPresent) {
            false -> {
                commentService.saveComment(comment)
                ResponseEntity(HttpStatus.OK)
            }
            else -> ResponseEntity(HttpStatus.FOUND)
        }
    }

    @PutMapping(consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun updateComment(comment: Comment): ResponseEntity<Any> {
        return when (commentService.findCommentByCommentId(comment.commentId).isPresent) {
            true -> {
                commentService.saveComment(comment)
                ResponseEntity(HttpStatus.OK)
            }
            else -> ResponseEntity(HttpStatus.NO_CONTENT)
        }
    }

    @DeleteMapping("/{commentId}", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun deleteComment(@PathVariable commentId: Long): ResponseEntity<Any> {
        return when (commentService.findCommentByCommentId(commentId).isPresent) {
            true -> {
                commentService.deleteCommentByCommentId(commentId)
                ResponseEntity(HttpStatus.OK)
            }
            else -> ResponseEntity(HttpStatus.NO_CONTENT)
        }
    }

}