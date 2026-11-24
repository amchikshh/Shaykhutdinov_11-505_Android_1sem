
package com.example.android_1sem

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                Screen()
            }
        }
    }

    @Composable
    fun Screen() {

        var sliderPosition by remember { mutableFloatStateOf(3f) }
        var input by remember { mutableStateOf("3") }
        var progress by remember { mutableFloatStateOf(0f) }

        val coroutineScope = rememberCoroutineScope()

        val n = input.toIntOrNull()
        val isValid = n != null && n in 0..10

        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {

            LinearProgressIndicator(
                progress = { progress },
                modifier = Modifier.fillMaxWidth()
            )

            Text("Введите количество корутин:")

            Text("Количество корутин: ${sliderPosition.roundToInt()}")

            Slider(
                value = sliderPosition,
                onValueChange = {
                    sliderPosition = it.roundToInt().toFloat()
                    input = sliderPosition.roundToInt().toString()
                },
                valueRange = 0f..10f,
                steps = 9
            )

            OutlinedTextField(
                value = input,
                onValueChange = { value ->
                    if (
                        value.isEmpty() ||
                        (
                                value.length <= 2 &&
                                        value.all { it.isDigit() } &&
                                        value.toIntOrNull()?.let { it in 0..10 } == true
                                )
                    ) {
                        input = value

                        val number = value.toIntOrNull()
                        if (number != null) {
                            sliderPosition = number.toFloat()
                        }
                    }
                },
                label = { Text("Количество корутин (0–10)") },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                ),
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            Button(
                onClick = {
                    progress = 0f
                    coroutineScope.coroutineContext.cancelChildren()

                    coroutineScope.launch {
                        for (i in 1..(n ?: 0)) {
                            letsWait()
                            progress = i.toFloat() / (n ?: 1)
                        }
                    }
                },
                enabled = isValid && n!! > 0,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Последовательно")
            }

            Button(
                onClick = {
                    progress = 0f
                    coroutineScope.coroutineContext.cancelChildren()

                    val amount = n ?: 0

                    if (amount > 0) {
                        coroutineScope.launch {
                            coroutineScope {
                                repeat(amount) {
                                    launch {
                                        letsWait()
                                        progress += 1f / amount
                                    }
                                }
                            }
                        }
                    }
                },
                enabled = isValid && n!! > 0,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Параллельно")
            }

            Button(
                onClick = {
                    coroutineScope.coroutineContext.cancelChildren()
                    progress = 0f
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Отменить")
            }
        }
    }

    private suspend fun letsWait() {
        delay(2000L)
    }
}
