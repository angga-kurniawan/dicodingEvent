package com.example.submissionfundamentalpertama.AllComponent.CustomAppBar

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LargeTopAppBar
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
            Color.White
        ),
        title = {
            Column {
                Text(
                    text = "Dicoding Event",
                    color = Color(0xFF000823),
                    modifier = Modifier.fillMaxWidth()
                )
                if (scrollBehavior.state.collapsedFraction < 0.1f) {
                    Text(
                        text = "Recommendation event for you",
                        fontSize = 15.sp,
                        color = Color(0xFF000823),
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