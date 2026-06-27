package com.jagadishvjr.hiltprep.domain.model


data class User(
    val id: Int,
    val email: String,
    val name: String,
    val phone: String,
    val username: String,
    val website: String
)