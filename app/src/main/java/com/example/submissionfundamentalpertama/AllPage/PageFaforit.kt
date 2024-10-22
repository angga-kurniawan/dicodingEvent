package com.example.submissionfundamentalpertama.AllPage

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.submissionfundamentalpertama.AllComponent.CustomCard.CardBesar
import com.example.submissionfundamentalpertama.AllComponent.CustomCard.CardKecil
import com.example.submissionfundamentalpertama.Api.ApiViewModel.DataDicodingEventViewModel
import com.example.submissionfundamentalpertama.Api.DataDicodingEvent
import com.example.submissionfundamentalpertama.Room.FavoriteViewModel

@Composable
fun PageFavorite(navController: NavController) {
    // Mengambil instance dari FavoriteViewModel
    val favoriteViewModel: FavoriteViewModel = viewModel()
    // Mengamati data favorit
    val favoriteEvents by favoriteViewModel.allFavorites.observeAsState(emptyList())

    // Mengambil data dari ViewModel saat composable pertama kali diluncurkan
    LazyColumn(
        modifier = Modifier
            .padding(start = 10.dp, end = 10.dp).fillMaxSize(),
        content = {
            item {
                Spacer(modifier = Modifier.padding(10.dp))
            }
            items(
                items = favoriteEvents,
                itemContent = {
                    CardBesar(
                        id = it.id,
                        catagory = it.catagory,
                        img = it.img,
                        name = it.name,
                        ownerName = it.ownerName,
                        summary = it.summary,
                        beginTime = it.beginTime,
                        endTime = it.endTime,
                        onClick = { id ->
                            Log.d("Get id card up coming", it.id.toString())
                            Log.d("Get id card up coming", id.toString())
                            navController.navigate("Detail/${id}")
                        }
                    )
                    Spacer(modifier = Modifier.padding(10.dp))
                }
            )

        }
    )
}