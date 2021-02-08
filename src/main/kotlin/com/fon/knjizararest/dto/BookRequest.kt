package com.fon.knjizararest.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class BookRequest(
        @JsonProperty("isbn")
        val ISBN: String,
        @JsonProperty("bookName")
        private val bookName: String,
        @JsonProperty("description")
        private val description: String,
        @JsonProperty("price")
        private val price: Double,
        @JsonProperty("stock")
        private val stock: Long,
        @JsonProperty("language")
        private val language: String,
        @JsonProperty("images")
        private val images: List<String>? = emptyList(),
        @JsonProperty("genres")
        private val genres: List<Long>,
        @JsonProperty("authors")
        private val authors: List<Long>,
        @JsonProperty("publisher")
        private val publisher: Long? = null
)
