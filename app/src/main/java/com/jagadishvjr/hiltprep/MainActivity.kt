package com.jagadishvjr.hiltprep

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.jagadishvjr.hiltprep.presentation.user.UserScreen
import com.jagadishvjr.hiltprep.ui.theme.HiltPrepTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            HiltPrepTheme {
                UserScreen()
            }
        }
    }
}