package com.fon.knjizararest.entity

import javax.persistence.*

@Entity
@Table(name = "publisher")
data class Publisher(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "publisher_id", nullable = false) val publisherId: Long,
        @Column(name = "publisher_name") val publisherName: String
)