package com.jagadishvjr.hiltprep.domain.usecase

import com.jagadishvjr.hiltprep.data.repository.UserRepositoryImpl
import com.jagadishvjr.hiltprep.domain.model.User
import com.jagadishvjr.hiltprep.domain.repository.UserRepository
import javax.inject.Inject

class GetUserUseCase @Inject constructor(
    private val repository: UserRepository
) {
    suspend operator fun invoke() : List<User>{
        return repository.getUsers()
    }

}