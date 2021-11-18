package com.example.formasterclass

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.formasterclass.data.RepositoryListViewModel
import com.example.formasterclass.data.RepositoryModel
import com.example.formasterclass.ui.theme.ForMasterClassTheme
import com.google.gson.Gson
import kotlin.reflect.typeOf

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            ForMasterClassTheme {
                NavHost(
                    navController = navController,
                    startDestination = Screens.LIST_REPOSITORY.toString()
                ) {
                    composable(Screens.LIST_REPOSITORY.toString()) {
                        ListRepositoryView(
                            navController = navController,
                            repositoryListViewModel = RepositoryListViewModel()
                        )
                    }
                    composable(
                        route = "${Screens.REPOSITORY_DETAILS}/{repositoryName}",
                        arguments = listOf(
                            navArgument("repositoryName") {
                                type = NavType.StringType
                            }
                        )
                    ) {
                        RepositoryDetails(it.arguments?.getString("repositoryName")?:"")
                    }
                }
            }
        }
    }
}
