package com.jagadishvjr.hiltprep.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jagadishvjr.hiltprep.domain.model.AppResult
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
            when (val result = useCase()) {
                is AppResult.Success -> {
                    _uiState.value = UserUiState.Success(result.data)
                }
                is AppResult.Error -> {
                    _uiState.value = UserUiState.Error(result.message)
                }
            }
        }
    }
}
