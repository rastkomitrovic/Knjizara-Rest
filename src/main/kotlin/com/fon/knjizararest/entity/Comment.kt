package com.fon.knjizararest.entity

import com.fasterxml.jackson.annotation.JsonBackReference
import javax.persistence.*

@Entity
@Table(name = "comment")
data class Comment(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "comment_id", nullable = false) val commentId: Long,
        @Column(name = "text", nullable = false) val text: String,
        @Column(name = "rating", nullable = false) val rating:Float,
        @ManyToOne @JoinColumn(name = "user_id", nullable = false) @JsonBackReference val user: User,
        @ManyToOne @JoinColumn(name = "book_id", nullable = false) @JsonBackReference val book: Book
)