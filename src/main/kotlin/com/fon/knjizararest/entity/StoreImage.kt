package com.fon.knjizararest.entity

import com.fasterxml.jackson.annotation.JsonBackReference
import javax.persistence.*

@Entity
@Table(name = "storeimage")
data class StoreImage(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "image_id", nullable = false) val imageId: Long,
        @Column(name = "image_encoding", nullable = false) val imageEncoding: String,
        @Column(name = "image_url", nullable = true) val imageUrl: String? = "",
        @ManyToOne @JoinColumn(name = "store_id", nullable = false) @JsonBackReference val store: Store
)