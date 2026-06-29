package com.jagadishvjr.hiltprep.data.mapper

import com.jagadishvjr.hiltprep.data.remote.dto.UserDto
import com.jagadishvjr.hiltprep.domain.model.Address
import com.jagadishvjr.hiltprep.domain.model.User

fun UserDto.toDomain() : User {
    return User(
        id = id,
        name = name,
        username = username,
        phone = phone,
        email = email,
        address = Address(
            street = address.street,
            suite = address.suite,
            city = address.city,
            zipcode = address.zipcode
        ),
        website = website
    )
}
