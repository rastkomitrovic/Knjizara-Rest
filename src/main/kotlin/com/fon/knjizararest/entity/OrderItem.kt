package com.fon.knjizararest.entity

import com.fasterxml.jackson.annotation.JsonBackReference
import javax.persistence.*

@Entity
@Table(name = "order_item")
data class OrderItem(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "entry_id", nullable = false) val entryId: Long,
        @ManyToOne @JoinColumn(name = "book_id", nullable = false) val book: Book,
        @ManyToOne @JoinColumn(name = "order_id", nullable = false) @JsonBackReference val order: Order,
        @Column(name = "quantity", nullable = false) val quantity: Long
)