package com.example.submissionfundamentalpertama.AllComponent.CustomAppBar

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoriteAppBar() {
    LargeTopAppBar(
        title = {
            Column (
                content = {

            Text(text = "Favorite Event", color = MaterialTheme.colorScheme.primary)
            Text(text = "Your Favorite", fontSize = 20.sp, color = MaterialTheme.colorScheme.onPrimary)
                }
            )
        },
        colors = TopAppBarDefaults.largeTopAppBarColors(
            MaterialTheme.colorScheme.background
        )
    )
}

@Preview
@Composable
private fun FavoriteAppBarPrev() {
    FavoriteAppBar()
}
