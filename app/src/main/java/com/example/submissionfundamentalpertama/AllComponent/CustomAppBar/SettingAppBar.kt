package com.example.submissionfundamentalpertama.AllComponent.CustomAppBar

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingAppBar() {
    LargeTopAppBar(
        title = {
            Text(text = "Setting", color = MaterialTheme.colorScheme.primary)
        },
        colors = TopAppBarDefaults.largeTopAppBarColors(
            MaterialTheme.colorScheme.background
        )
    )
}