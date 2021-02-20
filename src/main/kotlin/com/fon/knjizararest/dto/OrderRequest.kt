package com.fon.knjizararest.dto

data class OrderRequest (
    val orderId: String,
    val username: String,
    val items: List<OrderRequestItem>
)