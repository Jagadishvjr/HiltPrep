package com.jagadishvjr.hiltprep.data.repository

import com.jagadishvjr.hiltprep.data.mapper.toDomain
import com.jagadishvjr.hiltprep.data.remote.UserApiService
import com.jagadishvjr.hiltprep.domain.model.AppResult
import com.jagadishvjr.hiltprep.domain.model.User
import com.jagadishvjr.hiltprep.domain.repository.UserRepository
import kotlinx.coroutines.CancellationException
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val apiService: UserApiService
) : UserRepository{

    override suspend fun getUsers(): AppResult<List<User>> {
        return try {
            val users = apiService.getUsers().map { it.toDomain() }
            AppResult.Success(users)
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            AppResult.Error(e.message ?: "Failed to fetch users")
        }
    }

}
