package com.practices.githubusers

import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.practices.githubusers.internal.core.navigation.compositionLocal.LocalNavigator
import com.practices.githubusers.internal.core.navigation.compositionLocal.Navigator
import com.practices.githubusers.internal.domain.base.functional.Resource
import com.practices.githubusers.internal.domain.user.model.UserItem
import com.practices.githubusers.internal.domain.user.repository.UserRepository
import com.practices.githubusers.internal.domain.user.usercase.GetGithubUsersUseCase
import com.practices.githubusers.presentation.home.HomeScreen
import com.practices.githubusers.presentation.home.HomeViewModel
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
class HomeScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Mock
    private lateinit var userRepository: UserRepository

    private val mockNavigator: Navigator = mock()

    @Mock
    private lateinit var getGithubUsersUseCase: GetGithubUsersUseCase

    @Mock
    private lateinit var viewModel: HomeViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        getGithubUsersUseCase = GetGithubUsersUseCase(userRepository)
        viewModel = HomeViewModel(getGithubUsersUseCase)
    }

    @Test
    fun testUserListFetchedAndDisplayed() = runTest {
        // Mock the user data
        val mockUsers = listOf(
            UserItem(
                1,
                "John Doe",
                "https://avatars.githubusercontent.com/u/2?v=4",
                "https://api.github.com/users/defunkt"
            ),
            UserItem(
                2,
                "John Cena",
                "https://avatars.githubusercontent.com/u/3?v=4",
                "https://api.github.com/users/pjhyett"
            ),
        )
        `when`(userRepository.getGithubUsers(0))
            .thenReturn(
                flowOf(
                    Resource.Success(mockUsers)
                )
            )

        `when`(getGithubUsersUseCase.execute(GetGithubUsersUseCase.Params(0)))
            .thenReturn(
                flowOf(
                    Resource.Success(mockUsers)
                )
            )

        viewModel.fetchRemoteData()

        assertEquals(mockUsers, viewModel.state.value?.users)

        composeTestRule.setContent {
            CompositionLocalProvider(LocalNavigator provides mockNavigator) {
                HomeScreen(
                    viewModel.state.collectAsState().value
                )
            }
        }

        // Assert that the user list items are displayed
        mockUsers.forEach { user ->
            composeTestRule.onNodeWithText(user.name).assertExists()
            composeTestRule.onNodeWithText(user.url).assertExists()
        }
    }


}