package com.cloud_evalutaion.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.cloud_evalutaion.data.local.entities.FormEntryEntity
import com.cloud_evalutaion.viewmodel.FormEntriesViewModel

@Composable
fun FormEntriesScreen(
    formId: String,
    viewModel: FormEntriesViewModel = hiltViewModel()
) {
    val formEntries by viewModel.getFormEntries(formId).collectAsState(initial = emptyList())

    Scaffold(
        topBar = { TopAppBar(title = { Text("Form Entries") }) },

        floatingActionButton = {
            FloatingActionButton(onClick = { viewModel.addFormEntry(formId) }) {
                Icon(Icons.Default.Add, contentDescription = "Add Form Entry")
            }
        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            if (formEntries.isEmpty()) {
                Text(
                    text = "No entries found.",
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                )
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(16.dp)
                ) {
                    items(formEntries) { entry ->
                        FormEntryItem(entry)
                    }
                }
            }
        }
    }
}

@Composable
fun FormEntryItem(entry: FormEntryEntity) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        elevation = 4.dp
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Entry ID: ${entry.id}", style = MaterialTheme.typography.h6)
            Text(text = "Form ID: ${entry.formId}", style = MaterialTheme.typography.body1)
            Text(text = "Data: ${entry.data}", style = MaterialTheme.typography.body2)
        }
    }
}
