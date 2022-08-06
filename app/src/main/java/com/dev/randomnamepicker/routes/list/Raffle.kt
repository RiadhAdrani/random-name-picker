package com.dev.randomnamepicker.routes.list

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.dev.randomnamepicker.ui.composables.Title
import kotlin.random.Random

@Composable
fun RaffleDialog(viewModel: ListVM) {
    if (!viewModel.showRaffle) return

    val remaining = remember { viewModel.selected.toMutableStateList() }
    val recent = remember { mutableStateOf("") }
    val recentId = remember { mutableStateOf("Id will be displayed here.") }

    Dialog(
        properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = false
        ),
        onDismissRequest = { viewModel.showRaffle = false }) {
        Column(
            modifier = Modifier
                .clickable {
                    if (remaining.isEmpty()) {
                        recentId.value = "Picked all items"
                        recent.value = "Picked all items"
                        return@clickable
                    }

                    val index = Random.nextInt(remaining.size)
                    val item = remaining[index]

                    recentId.value = item
                    recent.value = viewModel.list.items.find { i -> i.id == item }!!.content
                    remaining.remove(item)
                }
                .fillMaxWidth()
                .background(MaterialTheme.colors.background)
                .padding(vertical = 32.dp, horizontal = 16.dp)
                .height(300.dp)
                .wrapContentHeight(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Title(
                text = recent.value.ifBlank { "Click to Pick !" },
                size = 32
            )
            Spacer(
                modifier = Modifier.height(10.dp)
            )
            Text(
                text = "Remaining : ${remaining.size}",
                textAlign = TextAlign.Center,
                maxLines = 1
            )
            Spacer(
                modifier = Modifier.height(16.dp)
            )
            Button(onClick = { viewModel.showRaffle = false }) {
                Text(text = "Close")
            }
        }
    }
}