package com.fon.knjizararest.entity

import com.fasterxml.jackson.annotation.JsonManagedReference
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "orders")
data class Order(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "order_id", nullable = false) val orderId: Long,
        @Column(name = "pay_pal_order_id", nullable = false) val payPalOrderId:String,
        @ManyToOne @JoinColumn(name = "user_id", nullable = false) val user: User,
        @Column(name = "date_created", nullable = false) val dateCreated: Date,
        @Column(name = "total", nullable = false) val total:Double,
        @OneToMany(mappedBy = "order", cascade = [CascadeType.PERSIST]) @JsonManagedReference val orderItems: List<OrderItem>
)