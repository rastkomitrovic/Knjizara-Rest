package com.fon.knjizararest.entity

import com.fasterxml.jackson.annotation.JsonBackReference
import javax.persistence.*

@Entity
@Table(name = "bookimage")
data class BookImage(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "image_id", nullable = false) val imageId: Long,
        @Column(name = "image_encoding", nullable = false) val imageEncoding: String,
        @Column(name = "image_url", nullable = true) val imageUrl: String? = "",
        @ManyToOne @JoinColumn(name = "book_id", nullable = false) @JsonBackReference var book: Book?
)