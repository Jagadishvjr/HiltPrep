package com.jagadishvjr.hiltprep

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.jagadishvjr.hiltprep.domain.model.Address
import com.jagadishvjr.hiltprep.domain.model.User
import com.jagadishvjr.hiltprep.presentation.UserScreen
import com.jagadishvjr.hiltprep.presentation.UserUiState
import com.jagadishvjr.hiltprep.presentation.WelcomeScreen
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

        composeTestRule.onNodeWithTag("loading_indicator").assertExists()
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

    @Test
    fun successState_showsUserAndAddress() {
        composeTestRule.setContent {
            HiltPrepTheme {
                UserScreen(
                    state = UserUiState.Success(
                        listOf(
                            User(
                                id = 1,
                                name = "Leanne Graham",
                                username = "Bret",
                                email = "leanne@example.com",
                                address = Address(
                                    street = "Kulas Light",
                                    suite = "Apt. 556",
                                    city = "Gwenborough",
                                    zipcode = "92998-3874"
                                ),
                                phone = "1234567890",
                                website = "hildegard.org"
                            )
                        )
                    )
                )
            }
        }

        composeTestRule.onNodeWithText("Leanne Graham (Bret)").assertExists()
        composeTestRule.onNodeWithText("leanne@example.com").assertExists()
        composeTestRule.onNodeWithText("Kulas Light, Apt. 556").assertExists()
        composeTestRule.onNodeWithText("Gwenborough - 92998-3874").assertExists()
    }

    @Test
    fun welcomeScreen_showsGetUsersButton() {
        composeTestRule.setContent {
            HiltPrepTheme {
                WelcomeScreen(onGetUsersClick = {})
            }
        }

        composeTestRule.onNodeWithTag("get_users_button").assertExists()
        composeTestRule.onNodeWithText("Get Users").assertExists()
    }
}
