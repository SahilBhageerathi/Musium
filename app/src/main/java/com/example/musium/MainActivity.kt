package com.example.musium

import android.animation.ObjectAnimator
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.musium.presentation.navigation.AppNavGraph
import com.example.musium.presentation.ui.splash.StartupState
import com.example.musium.presentation.ui.splash.StartupViewModel
import com.example.musium.ui.theme.MusiumTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlin.getValue

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: StartupViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().apply {
            setKeepOnScreenCondition { viewModel.state.value == StartupState.Booting }
            setOnExitAnimationListener { splashScreenViewProvider ->

                val zoomX = ObjectAnimator.ofFloat(
                    splashScreenViewProvider.iconView,
                    "scaleX",
                    0.4f,
                    0f
                )


                val zoomY = ObjectAnimator.ofFloat(
                    splashScreenViewProvider.iconView,
                    "scaleY",
                    0.4f,
                    0f
                )
                zoomX.duration = 500
                zoomY.duration = 500

                zoomX.doOnEnd {
                    splashScreenViewProvider.remove()
                }

                zoomY.doOnEnd {
                    splashScreenViewProvider.remove()
                }

                zoomX.start()
                zoomY.start()


            }
        }
        enableEdgeToEdge()
        setContent {
            MusiumTheme {
                Scaffold(modifier = Modifier.fillMaxSize()
                    .padding(0.dp,0.dp,0.dp,0.dp)) { innerPadding ->

                    Box(modifier = Modifier.padding(innerPadding)){
                        AppNavGraph()
                    }

                }
            }
        }
    }
}
