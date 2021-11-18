package com.example.formasterclass

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.formasterclass.data.RepositoryModel

@Composable
fun RepositoryDetails(repositoryName: String) {
    Column(modifier = Modifier
        .padding(8.dp)
        .fillMaxSize()) {
        Text(
            "You are on the page of detailed screen for repository $repositoryName",
            modifier = Modifier.fillMaxWidth()
        )
    }
}


@Preview
@Composable
fun PreviewRepositoryDetails() {
    RepositoryDetails("Kek")
}