package com.jagadishvjr.hiltprep

import com.jagadishvjr.hiltprep.data.remote.dto.AddressDto
import com.jagadishvjr.hiltprep.data.remote.UserApiService
import com.jagadishvjr.hiltprep.data.remote.dto.UserDto
import com.jagadishvjr.hiltprep.data.repository.UserRepositoryImpl
import com.jagadishvjr.hiltprep.domain.model.AppResult
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test


class FakeApiService : UserApiService{
    override suspend fun getUsers(): List<UserDto> {
        return listOf(
            UserDto(
            id = 123,
            name = "Jagadeesh",
            username = "vjr",
            address = AddressDto(
                street = "Main Street",
                suite = "Suite 101",
                city = "Hyderabad",
                zipcode = "500001"
            ),
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

        assertTrue(result is AppResult.Success)

        val users = (result as AppResult.Success).data

        assertEquals(1, users.size)

        assertEquals(users[0].name,"Jagadeesh")
        assertEquals("Hyderabad", users[0].address.city)


    }
}
