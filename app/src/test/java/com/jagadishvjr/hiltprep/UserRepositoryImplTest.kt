package com.jagadishvjr.hiltprep

import com.jagadishvjr.hiltprep.data.remote.UserApiService
import com.jagadishvjr.hiltprep.data.remote.dto.UserDto
import com.jagadishvjr.hiltprep.data.repository.UserRepositoryImpl
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test


class FakeApiService : UserApiService{
    override suspend fun getUsers(): List<UserDto> {
        return listOf(
            UserDto(
            id = 123,
            name = "Jagadeesh",
            username = "vjr",
            phone = "9502412221",
            email = "vjr@gmail.com",
            website = "www.wipro.com",
            )
        )
    }
}

class UserRepositoryImplTest {

    @Test
    fun `getUsers returns mapped domain users`() = runTest {

        val repository = UserRepositoryImpl(FakeApiService())

        val result = repository.getUsers()

        assertEquals(1, result.size)

        assertEquals(result[0].name,"Jagadeesh")


    }
}