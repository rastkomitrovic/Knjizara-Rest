package com.fon.knjizararest.entity

import javax.persistence.*

@Entity
@Table(name = "country")
data class Country(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "country_id", nullable = false) val countryId: Long,
        @Column(name = "country_name", nullable = false) val countryName: String,
        @Column(name = "country_name_short", nullable = false) val countryNameShort: String,
        @Column(name = "description", nullable = true) val description: String? = ""
)