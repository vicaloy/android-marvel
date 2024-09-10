package com.example.comics.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.comics.R
import com.example.comics.domain.model.Comics

@Composable
fun ComicsScreen(viewModel: ComicsViewModel) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()
    val comics = uiState.value.comics.collectAsLazyPagingItems()

    LazyColumn(verticalArrangement = Arrangement.spacedBy(32.dp)) {
        items(count = comics.itemCount) { index ->
            comics[index]?.let { comic ->
                ComicItem(
                    comic = comic,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }
        }
    }
    Progress(state = comics.loadState, modifier = Modifier.fillMaxSize())
    ErrorDialog(state = comics.loadState)
}

@Composable
fun Progress(state: CombinedLoadStates, modifier: Modifier = Modifier) {
    if (state.append is LoadState.Loading || state.refresh is LoadState.Loading) {
        Box(modifier = modifier) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}

@Composable
fun ErrorDialog(
    state: CombinedLoadStates, modifier: Modifier = Modifier
) {
    var showDialog by remember { mutableStateOf(true) }
    if (showDialog && (state.append is LoadState.Error || state.refresh is LoadState.Error)) {
        AlertDialog(
            modifier = modifier,
            onDismissRequest = { showDialog = false },
            title = { Text(text = stringResource(id = com.example.common.R.string.alert_title)) },
            text = { Text(text = stringResource(id = com.example.common.R.string.alert_message)) },
            confirmButton = {
                Button(onClick = { showDialog = false }) {
                    Text(
                        text = stringResource(id = com.example.common.R.string.alert_button_cancel),
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }
        )
    }
}

@Composable
fun ComicItem(comic: Comics, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(text = comic.name.toString(), style = MaterialTheme.typography.titleSmall)
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(comic.thumbnail.toString())
                .crossfade(true)
                .build(),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxWidth()
        )
    }
}