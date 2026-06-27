package com.jagadishvjr.hiltprep.presentation

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun UserScreen(viewModel: UserViewModel = hiltViewModel()) {
    val state by viewModel.uiState.collectAsState()

    when (state) {
        is UserUiState.Loading -> {
            CircularProgressIndicator()
        }

        is UserUiState.Success -> {
            val users = (state as UserUiState.Success).list
            LazyColumn {
                items(users) { user ->
                    Text(text = "${user.name} (${user.username})")
                    Text(text = user.email)
                }
            }
        }

        is UserUiState.Error -> {
            Text(text = (state as UserUiState.Error).error)
        }
    }
}
