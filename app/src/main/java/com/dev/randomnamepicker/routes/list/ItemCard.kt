package com.dev.randomnamepicker.routes.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.dev.randomnamepicker.R
import com.dev.randomnamepicker.models.Item

@Composable
fun ItemCard(item: Item, viewModel: ListVM) {

    val selected = viewModel.isSelected(item.id)
    val context = LocalContext.current

    Card(
        modifier = Modifier
            .padding(vertical = 3.dp)
            .fillMaxWidth()
            .clickable { viewModel.toggleSelected(item.id, context) },
        backgroundColor = if (selected) Color.DarkGray else MaterialTheme.colors.background,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = item.content,
                modifier = Modifier.weight(1f)
            )

            Spacer(modifier = Modifier.width(4.dp))

            if (selected) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_baseline_done_24),
                    contentDescription = "Item selected"
                )
                Spacer(modifier = Modifier.width(8.dp))
            }

            Button(
                onClick = { viewModel.removeItem(item.id, context) },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = MaterialTheme.colors.background
                )
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_baseline_delete_forever_24),
                    contentDescription = "Remove Item"
                )
            }
        }
    }
}