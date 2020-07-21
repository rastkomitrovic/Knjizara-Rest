package com.fon.knjizararest.entity

import javax.persistence.*

@Entity
@Table(name = "basket_entry")
data class BasketEntry(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "entry_id", nullable = false) val entryId: Long,
        @ManyToOne @JoinColumn(name = "book_id", nullable = false) val book: Book,
        @ManyToOne @JoinColumn(name = "basket_id", nullable = false) val basket: Basket,
        @Column(name = "quantity", nullable = false) val quantity: Long
)