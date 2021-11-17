package com.example.formasterclass

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun RepositoryDetails() {
    Column(modifier = Modifier.padding(8.dp)) {
        Row(
            modifier = Modifier
                .padding(0.dp, 16.dp, 0.dp, 16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = "Logo",
                modifier = Modifier
            )
        }
        Text("It is description for git repository ")
    }
}


@Preview
@Composable
fun PreviewRepositoryDetails() {
    RepositoryDetails()
}