package com.jagadishvjr.hiltprep

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.jagadishvjr.hiltprep.presentation.UserScreen
import com.jagadishvjr.hiltprep.presentation.UserUiState
import com.jagadishvjr.hiltprep.ui.theme.HiltPrepTheme
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class UserScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun loadingState_showsLoading(){
        composeTestRule.setContent {
            UserScreen(
                state = UserUiState.Loading
            )
        }
        
        composeTestRule.onNodeWithTag("loading_indicator")
    }

    @Test
    fun errorState_showsErrorMessage() {
        composeTestRule.setContent {
            HiltPrepTheme{
                UserScreen(state = UserUiState.Error("Something went wrong"))
            }
        }

        composeTestRule.waitForIdle()


        composeTestRule.onNodeWithTag("error_message").assertExists()
        composeTestRule.onNodeWithText("Something went wrong").assertExists()
    }
}