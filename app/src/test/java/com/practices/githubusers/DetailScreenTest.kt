package com.practices.githubusers

import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.practices.githubusers.internal.core.navigation.compositionLocal.LocalNavigator
import com.practices.githubusers.internal.core.navigation.compositionLocal.Navigator
import com.practices.githubusers.internal.domain.base.functional.Resource
import com.practices.githubusers.internal.domain.user.model.UserDetail
import com.practices.githubusers.internal.domain.user.repository.UserRepository
import com.practices.githubusers.internal.domain.user.usercase.GetProfileUseCase
import com.practices.githubusers.presentation.detail.DetailUserScreen
import com.practices.githubusers.presentation.detail.DetailUserViewModel
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class DetailScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Mock
    private lateinit var userRepository: UserRepository

    private val mockNavigator: Navigator = mock()

    @Mock
    private lateinit var getProfileUseCase: GetProfileUseCase

    @Mock
    private lateinit var viewModel: DetailUserViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        getProfileUseCase = GetProfileUseCase(userRepository)
        viewModel = DetailUserViewModel(getProfileUseCase)
    }

    @Test
    fun testUserDetailFetchedAndDisplayed() = runTest {
        // Mock the user data
        val mockUser = UserDetail(
            1,
            "John",
            avatar = "https://avatars.githubusercontent.com/u/2?v=4",
            location = "Vietnam",
            blogUrl = "",
            followers = 1,
            following = 2

        )
        `when`(userRepository.getProfile("John"))
            .thenReturn(
                flowOf(
                    Resource.Success(mockUser)
                )
            )

        `when`(getProfileUseCase.execute("John"))
            .thenReturn(
                flowOf(
                    Resource.Success(mockUser)
                )
            )

        viewModel.getProfile("John")

        assertEquals(mockUser, viewModel.state.value)

        composeTestRule.setContent {
            CompositionLocalProvider(LocalNavigator provides mockNavigator) {
                DetailUserScreen(
                    viewModel,
                    mockUser
                )
            }
        }

        // Assert that the user list items are displayed
        composeTestRule.onNodeWithText(mockUser.userName).assertExists()
        composeTestRule.onNodeWithText(mockUser.location).assertExists()
        composeTestRule.onNodeWithText("${mockUser.followers}").assertExists()
        composeTestRule.onNodeWithText("${mockUser.following}").assertExists()
    }
}