package com.fon.knjizararest.entity

import com.fasterxml.jackson.annotation.JsonBackReference
import javax.persistence.*

@Entity
@Table(name = "order_item")
data class OrderItem(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "item_id", nullable = false) val itemId: Long,
        @ManyToOne @JoinColumn(name = "book_id", nullable = false) val book: Book,
        @ManyToOne @JoinColumn(name = "order_id", nullable = true) @JsonBackReference var order: Order?,
        @Column(name = "quantity", nullable = false) val quantity: Long
)