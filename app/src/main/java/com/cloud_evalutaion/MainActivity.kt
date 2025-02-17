package com.cloud_evalutaion

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.cloud_evalutaion.ui.forms.FormEntriesScreen
import com.cloud_evalutaion.ui.forms.FormsScreen
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp()
        }
    }
}

@Composable
fun MyApp() {
    val navController: NavHostController = rememberNavController()

    NavHost(navController = navController, startDestination = "forms") {
        composable("forms") { FormsScreen(navController = navController) }
        composable("form_entries/{formId}") { backStackEntry ->
            val formId = backStackEntry.arguments?.getString("formId") ?: return@composable
            FormEntriesScreen(formId = formId)
        }
    }
}
