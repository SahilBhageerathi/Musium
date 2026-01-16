package com.example.musium.presentation.ui.auth.login

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.musium.di.SpotifyAuthEntryPoint
import com.example.musium.presentation.ui.auth.AuthEvent
import com.example.musium.presentation.ui.auth.AuthViewModel
import dagger.hilt.android.EntryPointAccessors

@Composable
fun LoginScreen(
    onBackClick: () -> Unit,
    onLoginClick: () -> Unit,
    onForgotPasswordClick: () -> Unit,
    onGoogleClick: () -> Unit,
    onFacebookClick: () -> Unit,
    onAppleClick: () -> Unit,
    onSignUpClick: () -> Unit,
//    viewModel: AuthViewModel = hiltViewModel(),
//    spotifyAuthLauncher: SpotifyAuthLauncher
) {

    val context = LocalContext.current

    val viewModel: AuthViewModel = hiltViewModel()


    val spotifyAuthLauncher = remember {
        EntryPointAccessors.fromApplication(
            context.applicationContext,
            SpotifyAuthEntryPoint::class.java
        ).spotifyAuthLauncher()
    }

    LaunchedEffect(Unit) {
        viewModel.authEvent.collect { event ->
            when (event) {
                AuthEvent.OnClickLoginBtnEvent -> spotifyAuthLauncher.launchAuth(context)
            }
        }
    }





    Scaffold(
        topBar = {
            LoginTopBar(onBackClick)
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentPadding = PaddingValues(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Spacer(Modifier.height(24.dp))
            }


            item {
                LoginHeader()
            }




            item {
                Spacer(Modifier.height(32.dp))
            }


            item {
                LoginForm(
                    onLoginClick = { viewModel.onSpotifyLoginClick() },
                    onForgotPasswordClick = onForgotPasswordClick
                )
            }


            item {
                Spacer(Modifier.height(32.dp))
            }



            item {
                SocialLoginSection(
                    onGoogleClick,
                    onFacebookClick,
                    onAppleClick
                )
            }



            item {
                Spacer(Modifier.height(20.dp))
            }


            item {
                SignUpFooter(onSignUpClick)
            }


            item {
                Spacer(Modifier.height(16.dp))
            }

        }
    }
}
