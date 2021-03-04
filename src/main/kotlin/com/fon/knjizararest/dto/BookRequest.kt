package com.fon.knjizararest.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class BookRequest(
        @JsonProperty("isbn")
        val ISBN: String,
        @JsonProperty("bookName")
        val bookName: String,
        @JsonProperty("description")
        val description: String,
        @JsonProperty("price")
        val price: Double,
        @JsonProperty("stock")
        val stock: Long,
        @JsonProperty("language")
        val language: String,
        @JsonProperty("images")
        val images: List<String>? = emptyList(),
        @JsonProperty("genres")
        val genres: List<Long>,
        @JsonProperty("authors")
        val authors: List<Long>,
        @JsonProperty("publisher")
        val publisher: Long
)
