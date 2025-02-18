package com.cloud_evalutaion.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Stepper(value: Int, onValueChange: (Int) -> Unit) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Button(onClick = { if (value > 0) onValueChange(value - 1) }) {
            Text("-")
        }
        Text(value.toString(), modifier = Modifier.padding(horizontal = 16.dp))
        Button(onClick = { if (value < 100) onValueChange(value + 1) }) {
            Text("+")
        }
    }
}
