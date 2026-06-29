package com.jagadishvjr.hiltprep

import com.jagadishvjr.hiltprep.domain.model.Address
import com.jagadishvjr.hiltprep.domain.model.AppResult
import com.jagadishvjr.hiltprep.domain.model.User
import com.jagadishvjr.hiltprep.domain.repository.UserRepository
import com.jagadishvjr.hiltprep.domain.usecase.GetUserUseCase
import com.jagadishvjr.hiltprep.presentation.UserUiState
import com.jagadishvjr.hiltprep.presentation.UserViewModel
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

class UserSuccessRepository : UserRepository{
    override suspend fun getUsers(): AppResult<List<User>> {
        return AppResult.Success(
            listOf(
            User(
                id = 111,
                name = "Jagadish",
                username = "vjrvjr",
                website = "www.wipro.com",
                address = Address(
                    street = "Road 45",
                    suite = "Flat 12",
                    city = "Hyderabad",
                    zipcode = "500081"
                ),
                phone = "9502412221",
                email = "vjr@gmail.com"
            )
            )
        )
    }
}

class UserErrorRepository : UserRepository{
    override suspend fun getUsers(): AppResult<List<User>> {
        return AppResult.Error("Network error")
    }
}


class UserViewModelTest2 {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `viewmodel emits success when repository returns data`() = runTest {


        val useCase = GetUserUseCase(UserSuccessRepository())

        val viewmodel = UserViewModel(useCase)

        advanceUntilIdle()


        val uiState = viewmodel.uiState.value

        assertTrue(uiState is UserUiState.Success)

    }
}










class UserViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val useCase = mockk<GetUserUseCase>()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `fetchUsers returns success when use case returns data`() = runTest {

        val fakeUsers = listOf(
            User(
                id = 11,
                name = "Jag",
                username = "vjr",
                email = "vjr@sample.com",
                website = "www.example.com",
                address = Address(
                    street = "KPHB",
                    suite = "Block A",
                    city = "Hyderabad",
                    zipcode = "500072"
                ),
                phone = "9502412221"
            )
        )

        coEvery { useCase.invoke() } returns AppResult.Success(fakeUsers)

        val vm = UserViewModel(useCase)

        advanceUntilIdle()

        val state = vm.uiState.value

        assertTrue(state is UserUiState.Success)

        assertEquals(fakeUsers, (state as UserUiState.Success).list)

        coVerify(exactly = 1) { useCase.invoke() }

    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `fetchUsers returns error when use case returns failure`() = runTest {
        coEvery { useCase.invoke() } returns AppResult.Error("Network error")

        val vm = UserViewModel(useCase)

        advanceUntilIdle()

        val state = vm.uiState.value

        assertTrue(state is UserUiState.Error)
        assertEquals("Network error", (state as UserUiState.Error).error)
    }

}

