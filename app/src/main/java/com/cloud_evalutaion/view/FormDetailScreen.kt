package com.cloud_evalutaion.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.cloud_evalutaion.components.FieldInput
import com.cloud_evalutaion.data.local.entities.FieldEntity
import com.cloud_evalutaion.model.FormField
import com.cloud_evalutaion.viewmodel.FormDetailViewModel

@Composable
fun FormDetailScreen(
    navController: NavController,
    formId: String,
    entryId: String,
    viewModel: FormDetailViewModel = hiltViewModel()
) {
    // Fetch form and form entry data
    LaunchedEffect(formId, entryId) {
        viewModel.loadForm(formId)
        viewModel.loadFormEntry(entryId)
    }

    val form by viewModel.form.collectAsState()
    val formEntry by viewModel.formEntry.collectAsState()

    // Show loading state if data is not yet available
    if (form == null || formEntry == null) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
        return
    }

    // Convert FormField to FieldEntity
    fun FormField.toFieldEntity(formId: String): FieldEntity {
        return FieldEntity(
            id = this.uuid,
            label = this.label,
            name = this.name,
            type = this.normalizedType,
            required = this.required,
            options = this.options?.joinToString(",") { it.value },
            index = this.index,
            formId = formId
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Fill Form Entry") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    Button(onClick = {
                        formEntry?.let { entry ->
                            viewModel.saveEntry(entry, {
                                navController.popBackStack()
                            }, { errorMessage ->
                                println("❌ Error: $errorMessage")
                            })
                        }
                    }) {
                        Text("Save")
                    }
                }
            )
        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            LazyColumn(modifier = Modifier.fillMaxSize().padding(16.dp)) {
                form?.sections?.let { sections ->
                    items(sections) { section ->
                        Text(text = section.title, style = MaterialTheme.typography.h6)

                        val sectionFields = form?.fields?.filter { it.index in section.from..section.to }
                            ?.sortedBy { it.index } ?: emptyList()

                        sectionFields.forEach { field ->
                            val fieldEntity = field.toFieldEntity(formId)
                            FieldInput(fieldEntity, viewModel)
                        }
                    }
                }
            }
        }
    }
}