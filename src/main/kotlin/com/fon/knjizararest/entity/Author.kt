package com.fon.knjizararest.entity

import com.fasterxml.jackson.annotation.JsonBackReference
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "author")
data class Author(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "author_id", nullable = false) val authorId: Long,
        @Column(name = "date_of_birth", nullable = false) val dateOfBirth: Date,
        @Column(name = "first_name", nullable = false) val firstName: String,
        @Column(name = "middle_name", nullable = true) val middleName: String? = "",
        @Column(name = "last_name", nullable = false) val lastName: String,
        @Column(name = "description", nullable = true) val description: String? = "",
        @ManyToMany @JoinTable(
                name = "book_author",
                joinColumns = [JoinColumn(name = "author_id", nullable = false)],
                inverseJoinColumns = [JoinColumn(name = "book_id", nullable = false)]
        ) @JsonBackReference val books: List<Book>
)