package com.dev.randomnamepicker.routes.list

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import com.dev.randomnamepicker.R
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.dev.randomnamepicker.getItems
import com.dev.randomnamepicker.ui.composables.Title

@Composable
fun ListRoute(navController: NavController, id: String?, viewModel: ListVM = hiltViewModel()) {

    val context = LocalContext.current

    LaunchedEffect(key1 = "no-key") {
        if (id == null) return@LaunchedEffect

        val items = getItems(context)
        val list = items.find { item -> item.id == id }

        viewModel.listOfLists = SnapshotStateList()
        viewModel.listOfLists.addAll(items)

        if (list != null) {
            viewModel.list = list

            viewModel.items = SnapshotStateList()
            viewModel.selected = SnapshotStateList()

            viewModel.items.addAll(list.items)
            viewModel.selected.addAll(list.selectedItems)
        }
    }

    Scaffold(modifier = Modifier.fillMaxSize()) {

        if (viewModel.showRaffle) RaffleDialog(viewModel)

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp)
        ) {
            Title(
                text = viewModel.list.name + " (${viewModel.selected.size}" +
                        "/${viewModel.items.size})",
                size = 26
            )

            Spacer(modifier = Modifier.height(16.dp))

            ListAddItem(viewModel)

            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn(modifier = Modifier.weight(1f)) {
                items(
                    viewModel.items.size,
                    key = { viewModel.items[it].id }) { index ->
                    val item = viewModel.items[index]
                    ItemCard(item, viewModel)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                FloatingActionButton(
                    onClick = { viewModel.showRaffle = true },
                    backgroundColor = MaterialTheme.colors.background
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_baseline_casino_24),
                        contentDescription = "Start"
                    )
                }
            }
        }
    }


}