package com.jagadishvjr.hiltprep.presentation

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun UserScreen(viewModel: UserViewModel = hiltViewModel()) {

    when (val state = viewModel.uiState) {

        is UserUiState.Loading -> {
            CircularProgressIndicator()
        }

        is UserUiState.Success -> {
            LazyColumn {
                items(state.list) { user ->
                    Text(text = "${user.name} (${user.username})")
                    Text(text = user.email)
                }
            }
        }

        is UserUiState.Error -> {
            Text(text = state.error)
        }
    }
}
