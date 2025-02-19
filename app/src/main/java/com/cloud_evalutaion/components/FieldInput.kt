package com.cloud_evalutaion.components

import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.*
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.cloud_evalutaion.data.local.entities.FieldEntity
import com.cloud_evalutaion.viewmodel.FormDetailViewModel
import androidx.compose.ui.Modifier

@Composable
fun FieldInput(field: FieldEntity, viewModel: FormDetailViewModel) {
    Column(modifier = Modifier.padding(vertical = 8.dp)) {
        if (field.type != "description") {
            Row {
                Text(field.label, color = MaterialTheme.colors.onSurface.copy(alpha = 0.8f))
                if (field.required) {
                    Text("*", color = MaterialTheme.colors.error)
                }
            }
        }

        when (field.type) {
            "number" -> {
                var value by remember { mutableStateOf(viewModel.formData[field.id]?.toIntOrNull() ?: 0) }

                Stepper(
                    value = value,
                    onValueChange = {
                        value = it
                        viewModel.formData[field.id] = it.toString()
                    }
                )
            }

            "dropdown" -> {
                var expanded by remember { mutableStateOf(false) }
                var selectedOption by remember { mutableStateOf(viewModel.formData[field.id] ?: "") }

                Box {
                    Button(onClick = { expanded = true }) {
                        Text(selectedOption.ifEmpty { "Select an option" })
                    }
                    DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                        field.options?.forEach { option ->
                            DropdownMenuItem(onClick = {
                                selectedOption = option.label
                                viewModel.formData[field.id] = option.label
                                expanded = false
                            }) {
                                Text(option.label)
                            }
                        }
                    }
                }
            }

            "description" -> {
                Text(text = field.label, modifier = Modifier.padding(vertical = 8.dp))
                var value by remember { mutableStateOf(viewModel.formData[field.id] ?: "") }
                TextField(
                    value = value,
                    onValueChange = {
                        value = it
                        viewModel.formData[field.id] = it
                    },
                    modifier = Modifier.fillMaxWidth()
                )
            }

            else -> {
                var value by remember { mutableStateOf(viewModel.formData[field.id] ?: "") }
                TextField(
                    value = value,
                    onValueChange = {
                        value = it
                        viewModel.formData[field.id] = it
                    },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text)
                )
            }
        }
    }
}
