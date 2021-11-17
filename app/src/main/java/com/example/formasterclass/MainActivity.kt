package com.example.formasterclass

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.example.formasterclass.data.RepositoryListState
import com.example.formasterclass.data.RepositoryListViewModel
import com.example.formasterclass.data.RepositoryModel
import com.example.formasterclass.data.sampleData
import com.example.formasterclass.ui.theme.ForMasterClassTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            ForMasterClassTheme {
                NavHost(navController = navController, startDestination = "listRepository") {
                    composable("listRepository") {
                        ListRepositoryView(
                            navController = navController,
                            repositoryListViewModel = RepositoryListViewModel()
                        )
                    }
                    composable("repositoryDetails") {
                        RepositoryDetails()
                    }
                }
            }
        }
    }
}
