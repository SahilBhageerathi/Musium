package com.example.musium.presentation.ui.auth.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.musium.R

@Composable
fun LoginForm(
    onLoginClick: () -> Unit,
    onForgotPasswordClick: () -> Unit
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var rememberMe by remember { mutableStateOf(false) }

    Column {
        EmailField(email) { email = it }

        Spacer(Modifier.height(16.dp))

        PasswordField(password) { password = it }

        Spacer(Modifier.height(12.dp))

        RememberMeRow(rememberMe) { rememberMe = it }

        Spacer(Modifier.height(24.dp))

        LoginButton(onLoginClick)

        Spacer(Modifier.height(16.dp))

        ForgotPassword(onForgotPasswordClick)
    }
}


@Composable
fun EmailField(value: String, onValueChange: (String) -> Unit) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier.fillMaxWidth(),
        label = { Text(stringResource(R.string.email)) },
        leadingIcon = {
            Icon(Icons.Outlined.Email, contentDescription = null)
        },
        singleLine = true
    )
}

@Composable
fun PasswordField(value: String, onValueChange: (String) -> Unit) {
    var visible by remember { mutableStateOf(false) }

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier.fillMaxWidth(),
        label = { Text(stringResource(R.string.password)) },
        leadingIcon = {
            Icon(Icons.Outlined.Lock, contentDescription = null)
        },
        trailingIcon = {
            IconButton(onClick = { visible = !visible }) {
                Icon(
                    imageVector = if (visible)
                        Icons.Outlined.Visibility
                    else
                        Icons.Outlined.VisibilityOff,
                    contentDescription = null
                )
            }
        },
        visualTransformation =
            if (visible) VisualTransformation.None
            else PasswordVisualTransformation(),
        singleLine = true
    )
}

@Composable
fun RememberMeRow(checked: Boolean, onCheckedChange: (Boolean) -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = checked,
            onCheckedChange = onCheckedChange
        )
        Text(text = stringResource(R.string.remember_me))
    }
}

@Composable
fun LoginButton(onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        shape = RoundedCornerShape(28.dp)
    ) {
        Text(text = stringResource(R.string.loginTxt))
    }
}


@Composable
fun ForgotPassword(onClick: () -> Unit) {
    TextButton(onClick = onClick) {
        Text(stringResource(R.string.forgot_password))
    }
}
@Composable
fun SocialLoginSection(
    onGoogleClick: () -> Unit,
    onFacebookClick: () -> Unit,
    onAppleClick: () -> Unit
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = stringResource(R.string.or_continue_with),
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(Modifier.height(16.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            SocialIcon(R.drawable.google, onGoogleClick)
            SocialIcon(R.drawable.fb, onFacebookClick)
            SocialIcon(R.drawable.apple, onAppleClick)
        }
    }
}

@Composable
fun SocialIcon(iconRes: Int, onClick: () -> Unit) {
    IconButton(onClick = onClick) {
        Image(
            painter = painterResource(iconRes),
            contentDescription = null,
            modifier = Modifier.size(40.dp)
        )
    }
}
@Composable
fun SignUpFooter(onSignUpClick: () -> Unit) {
    Row {
        Text(text = stringResource(R.string.no_account))
        Spacer(Modifier.width(4.dp))
        Text(
            text = stringResource(R.string.sign_up),
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.clickable(onClick = onSignUpClick)
        )
    }
}

