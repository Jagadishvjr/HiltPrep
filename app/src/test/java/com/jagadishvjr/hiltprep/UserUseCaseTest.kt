package com.jagadishvjr.hiltprep

import com.jagadishvjr.hiltprep.domain.model.AppResult
import com.jagadishvjr.hiltprep.domain.model.User
import com.jagadishvjr.hiltprep.domain.repository.UserRepository
import com.jagadishvjr.hiltprep.domain.usecase.GetUserUseCase
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class FakeUserRepository : UserRepository{
    override suspend fun getUsers(): AppResult<List<User>> {
        return AppResult.Success(
            listOf(
            User(
                id = 101,
                name = "Jag",
                username = "Vjr",
                phone = "9502412221",
                website = "www.wipro.com",
                email = "vjr@wipro.com"
            )
            )
        )
    }
}


class UserUseCaseTest {

    @Test
    fun `invokes returns users from repository`() = runTest {
        val useCase = GetUserUseCase(FakeUserRepository())

        val result = useCase()

        assertTrue(result is AppResult.Success)
        assertEquals(1, (result as AppResult.Success).data.size)
    }

}
