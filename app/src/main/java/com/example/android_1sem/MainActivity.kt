package com.example.android_1sem

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.example.android_1sem.Screens.firstScreen
import com.example.android_1sem.Screens.secondScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        NotificationHandler(this).initNotifications()
        handleNotificationIntent(intent, wasRunning = false)
        setContent {
            startActivity()
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        handleNotificationIntent(intent, wasRunning = true)
    }

    private fun handleNotificationIntent(intent: Intent?, wasRunning: Boolean) {
        val title = intent?.getStringExtra("notif_title") ?: return
        val body  = intent.getStringExtra("notif_body")
        val msg   = if (wasRunning) body else title
        if (!msg.isNullOrBlank()) {
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
        }
    }
}

@Composable
fun startActivity() {
    Surface {
        val backStack = rememberNavBackStack(First)

        NavDisplay(
            backStack = backStack,
            onBack = { backStack.removeLastOrNull() },
            entryProvider = entryProvider {
                entry<First> {
                    firstScreen(
                        onButtoncClick = {
                            backStack.add(Second)
                        }
                    )
                }

                entry<Second> {
                    secondScreen()
                }
            }
        )
    }
}