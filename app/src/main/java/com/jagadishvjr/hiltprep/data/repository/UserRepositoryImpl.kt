package com.jagadishvjr.hiltprep.data.repository

import com.jagadishvjr.hiltprep.data.mapper.toDomain
import com.jagadishvjr.hiltprep.data.remote.ApiService
import com.jagadishvjr.hiltprep.domain.model.User
import com.jagadishvjr.hiltprep.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : UserRepository{
    override suspend fun getUsers(): List<User> {
        return apiService.gerUsers().map { it.toDomain() }
    }

}