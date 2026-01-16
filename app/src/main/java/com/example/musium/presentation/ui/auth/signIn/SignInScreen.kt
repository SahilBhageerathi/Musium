package com.example.musium.presentation.ui.auth.signIn

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.musium.R

@Composable
fun SignInScreen(
    onBackClick: () -> Unit,
    onGoogleClick: () -> Unit,
    onFacebookClick: () -> Unit,
    onAppleClick: () -> Unit,
    onPasswordLoginClick: () -> Unit,
    onSignUpClick: () -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
            ,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            SignInTopBar(onBackClick)

            Spacer(modifier = Modifier.height(24.dp))

            SignInHeader()

            Spacer(modifier = Modifier.height(32.dp))

            SocialLoginSection(
                onGoogleClick = onGoogleClick,
                onFacebookClick = onFacebookClick,
                onAppleClick = onAppleClick
            )

            Spacer(modifier = Modifier.height(28.dp))

            DividerWithText(text = stringResource(R.string.or))

            Spacer(modifier = Modifier.height(24.dp))

            PasswordLoginButton(onPasswordLoginClick)

            Spacer(modifier = Modifier.height(20.dp))

            SignupFooter(onSignUpClick)
        }
    }
}


@Composable
fun DividerWithText(text: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Divider(modifier = Modifier.weight(1f))
        Text(
            text = text,
            modifier = Modifier.padding(horizontal = 12.dp),
            style = MaterialTheme.typography.labelMedium
        )
        Divider(modifier = Modifier.weight(1f))
    }
}


@Composable
fun PasswordLoginButton(onClick: () -> Unit) {
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        shape = RoundedCornerShape(28.dp),
        onClick = onClick
    ) {
        Text(
            text = stringResource(R.string.login_with_password),
            style = MaterialTheme.typography.titleMedium
        )
    }
}


@Composable
fun SignupFooter(onSignUpClick: () -> Unit) {
    Row {
        Text(
            text = stringResource(R.string.no_account),
            style = MaterialTheme.typography.bodyMedium
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = stringResource(R.string.sign_up),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.clickable(onClick = onSignUpClick)
        )
    }
}

