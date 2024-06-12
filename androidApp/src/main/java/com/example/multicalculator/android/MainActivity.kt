package com.example.multicalculator.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.multicalculator.android.ui.theme.MultiCalculatorTheme

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.multicalculator.Calculator
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
//            MyApplicationTheme {
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colorScheme.background
//                ) {
//                    Column(modifier = Modifier.padding()) {
//                        CalculatorView("Addition", "3 + 4", Calculator().add(3,4).toString())
//                        CalculatorView("Subtraction", "8 - 4", Calculator().subtract(8,4).toString())
//                        CalculatorView("Multiplication", "3 * 4", Calculator().multiply(3,4).toString())
//                        CalculatorView("Division", "8 / 4", Calculator().divide(8,4).toString())
//                    }
//                }
//            }

            MultiCalculatorTheme {
                CalcView()
            }
        }
    }
}

@Composable
fun CalcView() {
    val displayText = remember { mutableStateOf("0") }

    Column(modifier = Modifier
        .background(Color.LightGray)
        .fillMaxSize()
        .padding(16.dp)
    ) {
        Row {
            CalcDisplay(displayText)
        }
        Row {
            Column(modifier = Modifier.weight(3f)) {
                for (i in 7 downTo 1 step 3) {
                    CalcRow(displayText, i, 3)
                }
                Row {
                    CalcNumericButton(0, displayText)
                    CalcEqualsButton(displayText)
                }
            }
            Column(modifier = Modifier.weight(1f)) {
                val operations = listOf("+", "-", "*", "/")
                operations.forEach { operation ->
                    CalcOperationButton(operation, displayText)
                }
            }
        }
    }
}

@Composable
fun CalcDisplay(display: MutableState<String>) {
    Text(
        text = display.value,
        modifier = Modifier
            .height(50.dp)
            .padding(5.dp)
            .fillMaxWidth(),
        color = Color.Black
    )
}

@Composable
fun CalcRow(display: MutableState<String>, startNum: Int, numButtons: Int) {
    val endNum = startNum + numButtons
    Row(modifier = Modifier.padding(0.dp)) {
        for (i in startNum until endNum) {
            CalcNumericButton(i, display)
        }
    }
}

@Composable
fun CalcNumericButton(number: Int, display: MutableState<String>) {
    Button(
        onClick = {
            if (display.value == "0") {
                display.value = number.toString()
            } else {
                display.value += number.toString()
            }
        },
        modifier = Modifier.padding(4.dp)
    ) {
        Text(text = number.toString())
    }
}

@Composable
fun CalcOperationButton(operation: String, display: MutableState<String>) {
    Button(
        onClick = {
            display.value += " $operation "
        },
        modifier = Modifier.padding(4.dp)
    ) {
        Text(text = operation)
    }
}

@Composable
fun CalcEqualsButton(display: MutableState<String>) {
    Button(
        onClick = { display.value = "0" },
        modifier = Modifier.padding(4.dp)
    ) {
        Text(text = "=")
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
