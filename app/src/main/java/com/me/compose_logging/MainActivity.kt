package com.me.compose_logging

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.me.compose_logging.ui.theme.ComposeloggingTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber
import kotlin.concurrent.thread

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeloggingTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Greeting("Android")
                    Timber.d("onCreate: Inside MainActivity!!!")
                }
            }
        }
    }
}

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")

    val coroutineScope = rememberCoroutineScope()
    coroutineScope.launch(Dispatchers.IO) {
        Timber.d("Composable: from coroutine IO!!!")
    }

    thread {
        Timber.d("Composable: inside thread!!!")
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeloggingTheme {
        Greeting("Android")
    }
}