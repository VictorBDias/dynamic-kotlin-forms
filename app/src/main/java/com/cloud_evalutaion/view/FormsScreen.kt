package com.cloud_evalutaion.view

import com.cloud_evalutaion.data.local.entities.FormEntity
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.cloud_evalutaion.viewmodel.FormsViewModel
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun FormsScreen(
    viewModel: FormsViewModel = hiltViewModel(),
    navController: NavController
) {
    val forms by viewModel.forms.collectAsState()

    Scaffold(
        topBar = { TopAppBar(title = { Text("Forms") }) },
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(forms) { form ->
                    FormItem(form = form) {
                        navController.navigate("form_entries/${form.id}")
                    }
                }
            }
        }
    }
}

@Composable
fun FormItem(form: FormEntity, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable(onClick = onClick),
        elevation = 4.dp
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = form.title, fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Text(
                text = "Created on: ${formatDate(form.timestamp)}",
                fontSize = 14.sp,
                color = MaterialTheme.colors.onSurface.copy(alpha = 0.6f)
            )
        }
    }
}

fun formatDate(date: Date): String {
    val formatter = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
    return formatter.format(date)
}
