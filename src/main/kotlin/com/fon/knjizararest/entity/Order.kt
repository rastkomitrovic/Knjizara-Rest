package com.fon.knjizararest.entity

import com.fasterxml.jackson.annotation.JsonManagedReference
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "order")
data class Order(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "order_id", nullable = false) val orderId: Long,
        @ManyToOne @JoinColumn(name = "user_id", nullable = false) val user: User,
        @OneToMany(mappedBy = "order") @JsonManagedReference val orderItems: List<OrderItem>,
        @Column(name = "date_created") val dateCreated: Date
)