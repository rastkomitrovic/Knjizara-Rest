package com.fon.knjizararest.rest

import com.fon.knjizararest.dto.CommentRequest
import com.fon.knjizararest.entity.Comment
import com.fon.knjizararest.service.BookService
import com.fon.knjizararest.service.CommentService
import com.fon.knjizararest.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v0/comments")
class CommentRestController @Autowired constructor(
        val commentService: CommentService,
        val userService: UserService,
        val bookService: BookService
) {

    @CrossOrigin(origins = arrayOf("http://localhost:9099"))
    @GetMapping("/{bookId}", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun findCommentsByBookId(@PathVariable bookId: Long): ResponseEntity<List<Comment>> {
        val comments = commentService.findCommentsByBookBookId(bookId)
        return ResponseEntity(comments, HttpStatus.OK)
    }

    @PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun saveComment(@RequestBody comment: CommentRequest): ResponseEntity<Any> {
        if(commentService.existsCommentByUser(comment.username, comment.bookId))
            return ResponseEntity(HttpStatus.FOUND)
        val user=userService.findUserByUsername(comment.username)
        val book=bookService.findBookByBookId(comment.bookId)
        return when (user.isEmpty || book.isEmpty) {
            false -> {
                commentService.saveComment(Comment(-1L,comment.text,comment.rating,user.get(),book.get()))
                ResponseEntity(HttpStatus.OK)
            }
            else -> ResponseEntity(HttpStatus.NOT_ACCEPTABLE)
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