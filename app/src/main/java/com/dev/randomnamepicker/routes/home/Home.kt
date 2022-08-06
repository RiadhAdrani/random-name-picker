package com.dev.randomnamepicker.routes.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.dev.randomnamepicker.routes.ListCard
import com.dev.randomnamepicker.ui.composables.Title

@Composable
fun HomeRoute(navController: NavController, viewModel: HomeVM = hiltViewModel()) {

    val context = LocalContext.current

    LaunchedEffect("no-key") {
        viewModel.retrieveItems(context)
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        floatingActionButton = {
            FloatingActionButton(
                backgroundColor = MaterialTheme.colors.primary,
                onClick = {
                    viewModel.toggleModal(true)
                }) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = "Add list"
                )
            }
        }) {

        AddListDialog(viewModel, navController)

        Column(
            modifier = Modifier.padding(
                vertical = 12.dp,
                horizontal = 8.dp
            )
        ) {

            Title(
                text = "Random Name Picker",
                size = 28,
                align = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(12.dp))

            LazyColumn {
                items(viewModel.items.size) { index ->
                    val item = viewModel.items[index]

                    ListCard(
                        list = item,
                        navController = navController,
                        viewModel = viewModel
                    )
                }
            }
        }


    }
}