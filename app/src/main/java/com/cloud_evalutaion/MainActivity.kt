package com.cloud_evalutaion

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.cloud_evalutaion.ui.forms.FormEntriesScreen
import com.cloud_evalutaion.ui.forms.FormsScreen
import com.cloud_evalutaion.ui.viewmodel.FormsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val formsViewModel: FormsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launch {
            formsViewModel.loadFormsFromJson(this@MainActivity)
            formsViewModel.logForms()
        }

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
