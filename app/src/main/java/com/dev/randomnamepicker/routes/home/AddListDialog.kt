package com.dev.randomnamepicker.routes.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavController

@Composable
fun AddListDialog(viewModel: HomeVM, navController: NavController) {

    val context = LocalContext.current

    if (viewModel.showModal)
        Dialog(
            properties = DialogProperties(dismissOnBackPress = true),
            onDismissRequest = { viewModel.toggleModal(false) }) {

            val name = remember { mutableStateOf("") }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .background(MaterialTheme.colors.background)
                    .padding(vertical = 24.dp, horizontal = 16.dp),
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp),
                    text = "Create a new List",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Start
                )

                Spacer(modifier = Modifier.height(8.dp))

                TextField(
                    textStyle = TextStyle(fontSize = 18.sp),
                    label = { Text(text = "Enter the name of the list") },
                    value = name.value,
                    keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Words),
                    onValueChange = {
                        name.value = it
                    })

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp)
                ) {
                    Button(
                        onClick = { viewModel.addItem(name.value, context, navController) }) {
                        Text(text = "Create")
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Button(
                        onClick = { viewModel.toggleModal(false) }) {
                        Text(text = "Cancel")
                    }
                }

            }
        }

}