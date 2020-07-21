package com.fon.knjizararest.entity

import javax.persistence.*

@Entity
@Table(name = "city")
data class City(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "city_id", nullable = false) val cityId: Long,
        @Column(name = "city_name", nullable = false) val cityName: String,
        @Column(name = "postal_code", nullable = false) val postalCode: String,
        @Column(name = "description", nullable = true) val description: String? = "",
        @ManyToOne @JoinColumn(name = "country_id", nullable = false) val country: Country
)