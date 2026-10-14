package com.example.android_1sem

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp

private val emailRegex = Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")
@Composable
fun FirstScreen(
    onButtonClick: (email: String, password: String) -> Unit
) {
    val blankEmail = stringResource(R.string.blank_email)
    val incorrectEmail = stringResource(R.string.incorrect_email)
    val blankPassword = stringResource(R.string.blank_password)
    val incorrectPassword = stringResource(R.string.incorrect_password)

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    var emailError by remember {mutableStateOf<String?>(null)}
    var passwordError by remember { mutableStateOf<String?>(null) }

    fun validateUser() {
        emailError = when {
            email.isBlank() -> blankEmail
            !emailRegex.matches(email) -> incorrectEmail
            else -> null
        }

        passwordError = when {
            password.isBlank() -> blankPassword
            password.length < 8 -> incorrectPassword
            else -> null
        }

        if (emailError == null && passwordError == null) {
            onButtonClick(email, password)
        }
    }

    Column (modifier = Modifier
        .fillMaxSize()
        .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Text(stringResource(R.string.auth))
        Spacer(Modifier.height(24.dp))

        OutlinedTextField(
            value = email,
            onValueChange = {
                email = it
                emailError = null
            },
            isError = emailError != null,
            label = {Text(stringResource(R.string.email))},
            modifier = Modifier.fillMaxWidth()
        )
        emailError?.let {
            Text(
                text = it,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 4.dp, top = 4.dp)
            )
        }

        Spacer(Modifier.height(16.dp))

        OutlinedTextField(
            value = password,
            onValueChange = {
                password = it
                passwordError = null
            },
            isError = passwordError != null,
            label = {Text(stringResource(R.string.password))},
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = { validateUser() }
            ),
        )
        passwordError?.let {
            Text(
                text = it,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 4.dp, top = 4.dp)
            )
        }

        Spacer(Modifier.height(24.dp))

        Button(
            onClick = {
                validateUser()
            },
            Modifier.fillMaxWidth()
            ) {
            Text(stringResource(R.string.auth))
        }
    }
}