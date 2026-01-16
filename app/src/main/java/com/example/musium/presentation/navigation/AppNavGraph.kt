package com.example.musium.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSavedStateNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import androidx.navigation3.ui.rememberSceneSetupNavEntryDecorator
import com.example.musium.presentation.ui.auth.AuthViewModel
import com.example.musium.presentation.ui.auth.signIn.SignInScreen
import com.example.musium.presentation.ui.auth.login.LoginScreen
import com.example.musium.presentation.ui.home.HomeScreen
import com.example.musium.presentation.ui.onboarding.OnboardingScreen
import com.example.musium.presentation.ui.playList.PlaylistDetailsScreen


@Composable
fun AppNavGraph(
    viewModel: AuthViewModel = hiltViewModel()
) {

    val isLoggedIn by viewModel.isLoggedIn.collectAsState()

    val backStack = rememberNavBackStack<AppDestination>(AppDestination.Onboarding)


    LaunchedEffect(isLoggedIn) {
        if (isLoggedIn) {
            backStack.clear()
            backStack.add(AppDestination.Home)
        }
    }

    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryDecorators = listOf(
            rememberSavedStateNavEntryDecorator(),
            rememberSceneSetupNavEntryDecorator(),
        ),
        entryProvider = { key ->
            when (key) {
                is AppDestination.Onboarding -> NavEntry(key) {
                    OnboardingScreen(
                        onclickGetStarted = {
                            backStack.clear()
                            backStack.add(AppDestination.LoginScreen)
                        }
                    )
                }

                is AppDestination.SignInScreen -> NavEntry(key) {
                    SignInScreen(
                        onBackClick = { backStack.removeLastOrNull() },
                        onGoogleClick = {},
                        onFacebookClick = {},
                        onAppleClick = {},
                        onPasswordLoginClick = {},
                        onSignUpClick = {}
                    )
                }

                is AppDestination.LoginScreen -> NavEntry(key) {
                    LoginScreen(
                        onBackClick = { backStack.removeLastOrNull() },
                        onGoogleClick = {},
                        onFacebookClick = {},
                        onAppleClick = {},
                        onLoginClick = {},
                        onForgotPasswordClick = {},
                        onSignUpClick = {},
//                        viewModel = TODO(),
//                        spotifyAuthLauncher = TODO(),
                    )
                }

                is AppDestination.Home -> NavEntry(key) {
                    HomeScreen(
                        onPlayListClick = { playlist ->
                            backStack.add(
                                AppDestination.PlayListScreen(
                                    playlistId = playlist.id,
                                    playListImage = playlist.images[0].url,
                                    playListName = playlist.name,
                                    playListDescription = playlist.description,
                                )
                            )
                        }
                    )
                }

                is AppDestination.PlayListScreen -> NavEntry(key) {
                    PlaylistDetailsScreen(
                        playlistId = key.playlistId,
                        playListImage = key.playListImage!!,
                        playListName = key.playListName,
                        playListDescription = key.playListDescription!!,
                        onBack = { backStack.removeLastOrNull() }
                    )
                }


                else -> throw RuntimeException("Invalid Nav Key.")
            }
        }
    )

}