package com.example.android_1sem

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import com.example.android_1sem.ui.theme.Android_1semTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val name = stringResource(R.string.first_and_last_name)
            val groupNumber = stringResource(R.string.group_number)
            val buttonText = stringResource(R.string.button)
            val fullString = "$name/$groupNumber"
            Android_1semTheme() {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column (modifier = Modifier.padding(innerPadding)){
                        Text(name)
                        Text(groupNumber)
                        Button(onClick = {
                            val intent = Intent(this@MainActivity, SecondActivity::class.java)
                            intent.putExtra("student", fullString)
                            startActivity(intent)
                        })
                        {
                            Text(buttonText)
                        }
                    }
                }
            }
        }
    }
}
