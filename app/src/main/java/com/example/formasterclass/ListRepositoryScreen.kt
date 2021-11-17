package com.example.formasterclass

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
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.example.formasterclass.data.RepositoryListState
import com.example.formasterclass.data.RepositoryListViewModel
import com.example.formasterclass.data.RepositoryModel
import com.example.formasterclass.data.sampleData
import com.example.formasterclass.ui.theme.ForMasterClassTheme


@Composable
fun ListRepositoryView(
    navController: NavController,
    repositoryListViewModel: RepositoryListViewModel
) {
    val state = repositoryListViewModel.stateLiveData.observeAsState(RepositoryListState())

    if (state.value.listRepository.isEmpty()) {
        CenterCircularProgress()
    }
    LazyColumn {
        itemsIndexed(state.value.listRepository) { index, repository ->
            ItemRepository(
                repositoryModel = repository,
                navController = navController
            )
            if (index == state.value.listRepository.lastIndex) {
                PageLoadingCircularProgress()
                if(state.value.isLastPageLoaded.not()) {
                    repositoryListViewModel.onNextPageLoad()
                }
            }
        }
    }
}

@Composable
fun CenterCircularProgress() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator()
    }
}


@Composable
fun PageLoadingCircularProgress() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(8.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        CircularProgressIndicator()
    }
}


@Composable
fun ItemRepository(repositoryModel: RepositoryModel, navController: NavController) {
    var isExpanded by remember { mutableStateOf(false) }
    ForMasterClassTheme {
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 4.dp)
            .clickable { isExpanded = !isExpanded })
        {
            Image(
                painter = rememberImagePainter(
                    data = repositoryModel.urlAvatar,
                    builder = { transformations(CircleCropTransformation()) }),
                contentDescription = "Logo of repo",
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .border(1.5.dp, MaterialTheme.colors.secondary, CircleShape)
            )
            Spacer(Modifier.width(8.dp))
            Column {
                Text(
                    text = repositoryModel.name,
                    color = MaterialTheme.colors.secondaryVariant,
                    style = MaterialTheme.typography.subtitle2,
                    maxLines = if (isExpanded) Int.MAX_VALUE else 1
                )
                Spacer(Modifier.width(4.dp))
                Text(
                    text = repositoryModel.description,
                    style = MaterialTheme.typography.body2,
                    modifier = Modifier.padding(all = 4.dp),
                    maxLines = if (isExpanded) Int.MAX_VALUE else 1
                )
                if (isExpanded) {
                    TextButton(
                        onClick = { navController.navigate("repositoryDetails") },
                        modifier = Modifier.wrapContentSize()
                    ) {
                        Text(text = "more", fontSize = 12.sp)
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewItemRepository() {
    ForMasterClassTheme {
        LazyColumn {
            items(sampleData) { repository ->
                ItemRepository(
                    repositoryModel = repository,
                    rememberNavController()
                )
            }
        }
    }
}

