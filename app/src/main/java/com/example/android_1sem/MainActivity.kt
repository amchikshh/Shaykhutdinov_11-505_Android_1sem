package com.example.android_1sem

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface {
                    val backStack = rememberNavBackStack(first)

                    NavDisplay(
                        backStack = backStack,
                        onBack = { backStack.removeLastOrNull() },
                        entryProvider = entryProvider {

                            entry<first> {
                                firstScreen(
                                    onFilmClick = { index ->
                                        backStack.add(second(index))
                                    }
                                )
                            }

                            entry<second> { key ->
                                secondScreen(
                                    id = key.id,
                                )
                            }
                        }
                    )
                }
            }
        }
    }
}
