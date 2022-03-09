package com.carlosherrera.presentation.ui.screens.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.carlosherrera.domain.model.Error
import com.carlosherrera.domain.model.toPrintable

@Composable
fun ErrorMessage(error: Error) {
    Box(Modifier.fillMaxSize()) {
        Text(text = error.toPrintable(), modifier = Modifier.align(Alignment.Center), style = MaterialTheme.typography.h4, color = Color.Red)
    }
}