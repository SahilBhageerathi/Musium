package com.example.musium.presentation.ui.auth.signIn

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.musium.R

@Composable
fun SocialLoginSection(
    onGoogleClick: () -> Unit,
    onFacebookClick: () -> Unit,
    onAppleClick: () -> Unit
) {
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {

        SocialLoginButton(
            icon = R.drawable.google,
            text = stringResource(R.string.continue_with_google),
            onClick = onGoogleClick
        )

        SocialLoginButton(
            icon = R.drawable.fb,
            text = stringResource(R.string.continue_with_fb),
            onClick = onFacebookClick
        )

        SocialLoginButton(
            icon = R.drawable.apple,
            text = stringResource(R.string.continue_with_apple),
            onClick = onAppleClick
        )
    }
}

@Composable
fun SocialLoginButton(
    @DrawableRes icon: Int,
    text: String,
    onClick: () -> Unit
) {
    OutlinedButton(
        modifier = Modifier
            .fillMaxWidth()
            .height(54.dp),
        shape = RoundedCornerShape(14.dp),
        onClick = onClick
    ) {
        Image(
            painter = painterResource(icon),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.size(20.dp)
        )

        Spacer(modifier = Modifier.width(12.dp))

        Text(
            text = text,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}
