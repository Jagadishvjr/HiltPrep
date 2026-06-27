package com.jagadishvjr.hiltprep.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jagadishvjr.hiltprep.domain.model.User
import com.jagadishvjr.hiltprep.domain.usecase.GetUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class UserUiState {
    object Loading : UserUiState()
    data class Success(val list: List<User>): UserUiState()
    data class Error(val error: String): UserUiState()
}


@HiltViewModel
class UserViewModel @Inject constructor(
    private val useCase: GetUserUseCase
): ViewModel(){

    var uiState: UserUiState by mutableStateOf(UserUiState.Loading)
        private set

    init {
        fetchUsers()
    }

    fun fetchUsers() {
        uiState = UserUiState.Loading
        viewModelScope.launch {
            try {
                val userData = useCase.invoke()
                uiState = UserUiState.Success(userData)

            } catch (e: Exception) {
                uiState = UserUiState.Error(e.message ?: "Something went wrong")
            }
        }
    }
}
