package com.example.android_1sem

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.android_1sem.ui.theme.Android_1semTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Android_1semTheme {
                AppNavigation()
            }
        }
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    val generatedPassword = remember {PasswordGenerator.generate(8) }

    NavHost(
        navController = navController,
        startDestination = Screens.Auth
    ) {
        composable<Screens.Auth> {
            FirstScreen (
                onButtonClick = {email, password ->
                    navController.navigate(
                        Screens.Profile(email, password)
                    )
                }
            )
        }
        composable<Screens.Profile> { entry ->
            val args = entry.toRoute<Screens.Profile>()
            SecondScreen(
                email = args.email,
                password = args.password,
                generatedPassword = generatedPassword,
                onBack = {navController.popBackStack()}
            )
        }
    }
}
