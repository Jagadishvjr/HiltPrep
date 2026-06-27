package com.jagadishvjr.hiltprep.domain.repository

import com.jagadishvjr.hiltprep.domain.model.User

interface UserRepository{
    suspend fun getUsers() : List<User>
}