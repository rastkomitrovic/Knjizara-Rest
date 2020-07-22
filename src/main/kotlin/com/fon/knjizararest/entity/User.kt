package com.fon.knjizararest.entity

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "user")
data class User(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "user_id", nullable = false) val userId: Long,
        @Column(name = "username", unique = true, nullable = false) val username: String,
        @Column(name = "password", nullable = false) val password: String,
        @Column(name = "name", nullable = false) val name: String,
        @Column(name = "last_name", nullable = false) val lastName: String,
        @Column(name = "email", nullable = false) val email: String,
        @Column(name = "phone", nullable = false) val phone: String,
        @Column(name = "address", nullable = false) val address: String,
        @Column(name = "date_of_birth", nullable = false) val dateOfBirth: Date,
        @ManyToOne @JoinColumn(name = "city_id", nullable = false) val city: City
)