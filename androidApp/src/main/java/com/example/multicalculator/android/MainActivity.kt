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
import androidx.compose.runtime.saveable.rememberSaveable
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
    val displayText = rememberSaveable { mutableStateOf("0") }
    var leftNumber by rememberSaveable { mutableStateOf(0) }
    var rightNumber by rememberSaveable { mutableStateOf(0) }
    var operation by rememberSaveable { mutableStateOf("") }
    var complete by rememberSaveable { mutableStateOf(false) }

    if (complete && operation.isNotEmpty()) {
        var answer = 0
        when (operation) {
            "+" -> answer = leftNumber + rightNumber
            "-" -> answer = leftNumber - rightNumber
            "*" -> answer = leftNumber * rightNumber
            "/" -> if (rightNumber != 0) answer = leftNumber / rightNumber
        }
        displayText.value = answer.toString()
    } else if (operation.isNotEmpty() && !complete) {
        displayText.value = rightNumber.toString()
    } else {
        displayText.value = leftNumber.toString()
    }

    fun numberPress(btnNum: Int) {
        if (complete) {
            leftNumber = 0
            rightNumber = 0
            operation = ""
            complete = false
        }
        if (operation.isNotEmpty() && !complete) {
            rightNumber = rightNumber * 10 + btnNum
        } else if (operation.isEmpty() && !complete) {
            leftNumber = leftNumber * 10 + btnNum
        }
    }

    fun operationPress(op: String) {
        if (!complete) {
            operation = op
        }
    }

    fun equalsPress() {
        complete = true
    }

    Column(modifier = Modifier.background(Color.LightGray).fillMaxSize().padding(16.dp)) {
        Row {
            CalcDisplay(display = displayText)
        }
        Row {
            Column(modifier = Modifier.weight(1f)) {
                for (i in 7 downTo 1 step 3) {
                    CalcRow(onPress = { number -> numberPress(number) }, startNum = i, numButtons = 3)
                }
                Row {
                    CalcNumericButton(number = 0, onPress = { number -> numberPress(number) })
                    CalcEqualsButton(onPress = { equalsPress() })
                }
            }
            Column(modifier = Modifier.weight(0.25f)) {
                CalcOperationButton(operation = "+", onPress = { op -> operationPress(op) })
                CalcOperationButton(operation = "-", onPress = { op -> operationPress(op) })
                CalcOperationButton(operation = "*", onPress = { op -> operationPress(op) })
                CalcOperationButton(operation = "/", onPress = { op -> operationPress(op) })
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
fun CalcRow(onPress: (number: Int) -> Unit, startNum: Int, numButtons: Int) {
    val endNum = startNum + numButtons
    Row(modifier = Modifier.padding(0.dp)) {
        for (i in startNum until endNum) {
            CalcNumericButton(number = i, onPress = onPress)
        }
    }
}

@Composable
fun CalcNumericButton(number: Int, onPress: (number: Int) -> Unit) {
    Button(
        onClick = { onPress(number) },
        modifier = Modifier.padding(4.dp)
    ) {
        Text(text = number.toString())
    }
}

@Composable
fun CalcOperationButton(operation: String, onPress: (operation: String) -> Unit) {
    Button(
        onClick = { onPress(operation) },
        modifier = Modifier.padding(4.dp)
    ) {
        Text(text = operation)
    }
}

@Composable
fun CalcEqualsButton(onPress: () -> Unit) {
    Button(
        onClick = onPress,
        modifier = Modifier.padding(4.dp)
    ) {
        Text(text = "=")
    }
}


//@Composable
//fun titleView(): String  {
//    return "Calculator"
//}

//@Composable
//fun CalculatorView(label: String, numbers: String, result: String) {
//    Text(text = "$label ($numbers) = $result")
//}
//
//@Preview
//@Composable
//fun DefaultPreview() {
//    MyApplicationTheme {
//        CalculatorView("Hello, Android!", "1,2","Calculator")
//    }
//}
