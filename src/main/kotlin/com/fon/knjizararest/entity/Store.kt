package com.fon.knjizararest.entity

import javax.persistence.*

@Entity
@Table(name = "store")
data class Store(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "store_id", nullable = false) val storeId: Long,
        @Column(name = "store_name", nullable = false) val storeName: String,
        @Column(name = "description", nullable = true) val description: String? = "",
        @Column(name = "address", nullable = false) val address: String,
        @ManyToOne @JoinColumn(name = "city_id", nullable = false) val city: City,
        @OneToMany(mappedBy = "store")  val images: List<StoreImage>
)