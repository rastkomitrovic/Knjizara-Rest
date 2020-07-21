package com.fon.knjizararest.entity

import javax.persistence.*

@Entity
@Table(name = "genre")
data class Genre(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "genre_id", nullable = false) val genreId: Long,
        @Column(name = "genre_name", nullable = false) val genreName: String,
        @Column(name = "description", nullable = true) val description: String? = "",
        @ManyToMany @JoinTable(
                name = "book_genre",
                joinColumns = [JoinColumn(name = "genre_id", nullable = false)],
                inverseJoinColumns = [JoinColumn(name = "book_id", nullable = false)]
        ) val books: List<Book>
)