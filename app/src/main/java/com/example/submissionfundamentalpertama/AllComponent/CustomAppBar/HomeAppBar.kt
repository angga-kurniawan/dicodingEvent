package com.example.submissionfundamentalpertama.AllComponent.CustomAppBar

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.submissionfundamentalpertama.Adapter.DarkMode.DarkModeAndLight

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeAppBar(scrollBehavior: TopAppBarScrollBehavior) {
    LargeTopAppBar(
        modifier = Modifier
            .shadow(
                40.dp,
                ambientColor = Color(0xFF00B0FF),
                spotColor = Color(0xFF00B0FF)
            ),
        scrollBehavior = scrollBehavior,
        colors = TopAppBarDefaults.topAppBarColors(
           containerColor =  MaterialTheme.colorScheme.background,
            scrolledContainerColor = MaterialTheme.colorScheme.background
        ),
        title = {
            Column {
                Text(
                    text = "Dicoding Event",
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.fillMaxWidth()
                )
                if (scrollBehavior.state.collapsedFraction < 0.1f) {
                    Text(
                        text = "Recommendation event for you",
                        fontSize = 15.sp,
                        color = MaterialTheme.colorScheme.onPrimary,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
private fun Pilprev() {
    HomeAppBar(TopAppBarDefaults.exitUntilCollapsedScrollBehavior())
}