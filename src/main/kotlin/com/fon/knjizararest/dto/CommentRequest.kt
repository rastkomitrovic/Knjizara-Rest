package com.fon.knjizararest.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class CommentRequest(
        @JsonProperty("text") val text:String,
        @JsonProperty("rating") val rating:Int,
        @JsonProperty("bookId") val bookId:Long,
        @JsonProperty("username") val username:String
)