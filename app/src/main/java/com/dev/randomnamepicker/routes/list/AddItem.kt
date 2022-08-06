package com.dev.randomnamepicker.routes.list

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import com.dev.randomnamepicker.R

@Composable
fun ListAddItem(viewModel: ListVM) {

    val context = LocalContext.current

    Row(
        Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        TextField(
            value = viewModel.field,
            onValueChange = { viewModel.field = it },
            modifier = Modifier.weight(1f),
            keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Words),
            label = { Text(text = "Enter a new name") }
        )
        Spacer(modifier = Modifier.fillMaxHeight().padding(4.dp))
        Button(
            modifier = Modifier.fillMaxHeight(),
            onClick = { viewModel.addItem(context) }) {
            Icon(
                painter = painterResource(id = R.drawable.ic_baseline_playlist_add_24),
                contentDescription = "Removed"
            )
        }
    }
}
