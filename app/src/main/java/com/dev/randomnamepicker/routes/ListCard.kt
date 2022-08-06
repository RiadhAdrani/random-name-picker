package com.dev.randomnamepicker.routes

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.currentCompositionLocalContext
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.dev.randomnamepicker.R
import com.dev.randomnamepicker.models.ItemList
import com.dev.randomnamepicker.navigation.Route
import com.dev.randomnamepicker.routes.home.HomeVM

@Composable
fun ListCard(list: ItemList, navController: NavController, viewModel: HomeVM) {

    val context = LocalContext.current

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { navController.navigate("${Route.List.path}/${list.id}") },
        backgroundColor = MaterialTheme.colors.background,
        elevation = 8.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = list.name,
                color = Color.White,
                fontSize = 20.sp,
            )
            IconButton(onClick = { viewModel.removeItem(list.id, context) }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_baseline_delete_forever_24),
                    contentDescription = "Delete list"
                )
            }
        }

    }
}