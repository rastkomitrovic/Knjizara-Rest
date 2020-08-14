package com.fon.knjizararest.entity

import com.fasterxml.jackson.annotation.JsonBackReference
import javax.persistence.*

@Entity
@Table(name = "basket_entry")
data class BasketEntry(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "entry_id", nullable = false) val entryId: Long,
        @ManyToOne @JoinColumn(name = "book_id", nullable = false) val book: Book,
        @ManyToOne @JoinColumn(name = "basket_id", nullable = false) @JsonBackReference val basket: Basket,
        @Column(name = "quantity", nullable = false) val quantity: Long,
        @Column(name = "active", nullable = false) val active: Boolean
)