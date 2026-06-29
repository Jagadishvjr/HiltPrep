package com.jagadishvjr.hiltprep.domain.model

data class Address(
    val street: String,
    val suite: String,
    val city: String,
    val zipcode: String
)

data class User(
    val id: Int,
    val email: String,
    val name: String,
    val address: Address,
    val phone: String,
    val username: String,
    val website: String
)
