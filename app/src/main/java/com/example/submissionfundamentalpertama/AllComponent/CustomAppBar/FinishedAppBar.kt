package com.example.submissionfundamentalpertama.AllComponent.CustomAppBar

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FinishedAppBar(
    inputSearch: String,
    onChangeInputSearch: (String) -> Unit,
    onSearch: (String) -> Unit
) {
    TopAppBar(
        title = {
            OutlinedTextField(
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "search",
                        tint = Color(0xFF000823)
                    )
                },
                shape = RoundedCornerShape(10.dp),
                visualTransformation = VisualTransformation.None,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 20.dp)
                    .onKeyEvent {
                        if (it.key == Key.Enter) {
                            onSearch(it.toString())
                            true
                        }else{
                            false
                        }
                    },
                value = inputSearch,
                onValueChange = {
                    onChangeInputSearch(it)
                    onSearch(it)
                },
                textStyle = TextStyle(
                    color = Color(0xFF000823)
                ),
                singleLine = true
            )
        },
        modifier = Modifier.shadow(
            40.dp,
            ambientColor = Color(0xFF00B0FF),
            spotColor = Color(0xFF00B0FF)
        ),
        colors = TopAppBarDefaults.largeTopAppBarColors(
            //            Color(0xFF000823)
            Color.White
        )
    )
}

@Preview
@Composable
private fun Tampil() {
    FinishedAppBar("", {},{})
}