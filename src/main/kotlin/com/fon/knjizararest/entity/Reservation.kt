package com.fon.knjizararest.entity

import javax.persistence.*

@Entity
@Table(name = "reservation")
data class Reservation(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "reservation_id") val reservationId: Long,
        @ManyToOne @JoinColumn(name = "entry_id", nullable = false) val entry: OrderItem,
        @Column(name = "status", nullable = false) val status: String
)