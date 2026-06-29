package com.jagadishvjr.hiltprep.data.remote.dto

data class AddressDto(
    val street: String,
    val suite: String,
    val city: String,
    val zipcode: String
)

data class UserDto(
    val id: Int,
    val email: String,
    val name: String,
    val address: AddressDto,
    val phone: String,
    val username: String,
    val website: String
)
