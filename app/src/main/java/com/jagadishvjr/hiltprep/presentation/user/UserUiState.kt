package com.jagadishvjr.hiltprep.presentation.user

import com.jagadishvjr.hiltprep.domain.model.User

sealed interface UserUiState{
    data object Loading : UserUiState
    data class Success(val users : List<User>) : UserUiState
    data class Error(val message: String) : UserUiState
}