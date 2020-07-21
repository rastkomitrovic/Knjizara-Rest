package com.fon.knjizararest.entity

import javax.persistence.*

@Entity
@Table(name = "book")
data class Book(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "book_id", nullable = false) val bookId: Long,
        @Column(name = "ISBN", nullable = false) val ISBN: String,
        @Column(name = "book_name", nullable = false) val bookName: String,
        @Column(name = "description", nullable = true) val description: String? = "",
        @Column(name = "price", nullable = false) val price: Double,
        @Column(name = "stock", nullable = false) val stock: Long,
        @Column(name = "number_of_sold_copies", nullable = false) val numberOfSoldCopies: Long,
        @Column(name = "language", nullable = false) val language: String,
        @OneToMany(mappedBy = "book") val images: List<BookImage>,
        @ManyToMany(mappedBy = "books") val genres: List<Genre>,
        @ManyToMany(mappedBy = "books") val authors: List<Author>
)