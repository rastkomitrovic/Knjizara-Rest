package com.fon.knjizararest.entity

import com.fasterxml.jackson.annotation.JsonManagedReference
import javax.persistence.*

@Entity
@Table(name = "basket")
data class Basket(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "basket_id", nullable = false) val basketId: Long,
        @OneToOne @JoinColumn(name = "user_id", nullable = false) val user: User,
        @OneToMany(mappedBy = "basket") @JsonManagedReference val entries: List<BasketEntry>
)