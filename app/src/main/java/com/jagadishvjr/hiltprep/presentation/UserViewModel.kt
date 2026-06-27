package com.jagadishvjr.hiltprep.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jagadishvjr.hiltprep.domain.model.User
import com.jagadishvjr.hiltprep.domain.usecase.GetUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
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

    private val _uiState = MutableStateFlow<UserUiState>(UserUiState.Loading)
    val uiState: StateFlow<UserUiState> = _uiState.asStateFlow()

    init {
        fetchUsers()
    }

    fun fetchUsers() {
        _uiState.value = UserUiState.Loading
        viewModelScope.launch {
            try {
                val userData = useCase.invoke()
                _uiState.value = UserUiState.Success(userData)

            } catch (e: Exception) {
                _uiState.value = UserUiState.Error(e.message ?: "Something went wrong")
            }
        }
    }
}
