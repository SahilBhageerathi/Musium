package com.example.musium.presentation.ui.onboarding


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.musium.R
import com.example.musium.presentation.ui.onboarding.widgets.OnboardingText


@Composable
fun OnboardingScreen(
    viewModel: OnboardingViewModel = hiltViewModel(),
    onclickGetStarted: () -> Unit
) {
    val state = viewModel.state.collectAsStateWithLifecycle()
    val cardHeight = remember { mutableStateOf(0.dp) }
    val density = LocalDensity.current
    when (state.value) {
        is OnboardingState.Normal -> {
            Box(modifier = Modifier.fillMaxSize()) {
                Image(
                    painter = painterResource(R.drawable.welcome),
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier.fillMaxSize()
                )

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter),
                ) {
                    Image(
                        painter = painterResource(R.drawable.img_girl),

                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .offset(y=-(cardHeight.value.minus(20.dp))),
                        contentScale = ContentScale.Crop,

                        )
                    OnboardingCard(
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .onGloballyPositioned{
                                val heightPx = it.size.height
                                cardHeight.value = with(density){heightPx.toDp()}
                            },
                        onclickGetStarted = onclickGetStarted
                    )
                }


            }
        }
    }

}

@Composable
fun OnboardingCard(modifier: Modifier,onclickGetStarted: ()->Unit) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(
                RoundedCornerShape(
                    topStart = 16.dp,
                    topEnd = 16.dp,
                    bottomStart = 0.dp,
                    bottomEnd = 0.dp
                )
            )
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp, 0.dp, 16.dp, 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center

    ) {

        OnboardingText(
            textResourceId = R.string.onboarding_text,
            spanStyle = SpanStyle(color = MaterialTheme.colorScheme.primary),
            modifier = Modifier.padding(16.dp),
            textStyle = MaterialTheme.typography.headlineSmall,
            textAlign = TextAlign.Center
        )
        Row(
            modifier = Modifier
                .padding(vertical = 16.dp)
                .width(70.dp)
                .height(8.dp)
                .clip(RoundedCornerShape(5.dp))
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
                    .background(MaterialTheme.colorScheme.primary)
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
                    .background(MaterialTheme.colorScheme.onSurface)
            )

        }

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {onclickGetStarted()}) {
            Text(
                text = stringResource(R.string.get_started)
            )
        }

        Box(
            modifier = Modifier
                .height(20.dp)

        )

    }
}