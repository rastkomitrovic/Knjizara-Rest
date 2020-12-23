package com.fon.knjizararest.entity

import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonManagedReference
import javax.persistence.*


@Entity
@Table(name = "book")
data class Book(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "book_id", nullable = false) val bookId: Long,
        @Column(name = "isbn", nullable = false) val ISBN: String,
        @Column(name = "book_name", nullable = false) val bookName: String,
        @Column(name = "description", nullable = true) val description: String? = "",
        @Column(name = "price", nullable = false) val price: Double,
        @Column(name = "stock", nullable = false) val stock: Long,
        @Column(name = "number_of_sold_copies", nullable = false) val numberOfSoldCopies: Long,
        @Column(name = "language", nullable = false) val language: String,
        @OneToMany(mappedBy = "book") @JsonManagedReference val images: List<BookImage>,
        @ManyToMany(mappedBy = "books") @JsonManagedReference val genres: List<Genre>,
        @ManyToMany(mappedBy = "books") @JsonManagedReference val authors: List<Author>,
        @OneToMany(mappedBy = "book") @JsonManagedReference val comments: List<Comment>,
        @Column(name = "rating", nullable = false) val rating: Float,
        @ManyToOne @JoinColumn(name = "publisher_id", nullable = false) val publisher: Publisher
){
    override fun toString(): String {
        return bookName
    }
}
