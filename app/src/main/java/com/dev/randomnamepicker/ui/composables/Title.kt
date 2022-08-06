package com.dev.randomnamepicker.ui.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Title(
    text: String,
    color: Color = MaterialTheme.colors.onBackground,
    size: Int = 20,
    align: TextAlign = TextAlign.Center
) {
    Text(
        text = text,
        color = color,
        fontSize = size.sp,
        fontWeight = FontWeight.Bold,
        textAlign = align,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        maxLines = 1
    )
}