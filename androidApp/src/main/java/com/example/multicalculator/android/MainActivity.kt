package com.example.multicalculator.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.multicalculator.Greeting
import com.example.multicalculator.Calculator
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column(modifier = Modifier.padding()) {
                        CalculatorView("Addition", "3 + 4", Calculator().add(3,4).toString())
                        CalculatorView("Subtraction", "8 - 4", Calculator().subtract(8,4).toString())
                        CalculatorView("Multiplication", "3 * 4", Calculator().multiply(3,4).toString())
                        CalculatorView("Division", "8 / 4", Calculator().divide(8,4).toString())
                    }
                }
            }
        }
    }
}

@Composable
fun titleView(): String  {
    return "Calculator"
}

@Composable
fun CalculatorView(label: String, numbers: String, result: String) {
    Text(text = "$label ($numbers) = $result")
}

@Preview
@Composable
fun DefaultPreview() {
    MyApplicationTheme {
        CalculatorView("Hello, Android!", "1,2","Calculator")
    }
}
