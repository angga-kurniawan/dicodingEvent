package com.example.submissionfundamentalpertama.Adapter

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector

sealed class DataBottomBar(
    val route: String,
    val icon: ImageVector,
    val title: String? = null
) {
    data object Home : DataBottomBar(
        route = "Home",
        icon = Icons.Default.Home,
        title = "Home"
    )

    data object UpComing : DataBottomBar(
        route = "Up Coming",
        icon = Icons.Default.DateRange,
        title = "Up-Coming"
    )

    data object Finished : DataBottomBar(
        route = "Finished",
        icon = Icons.Default.AccountBox,
        title = "Finshed"
    )
}