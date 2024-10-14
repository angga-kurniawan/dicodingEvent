package com.example.submissionfundamentalpertama.AllPage

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.submissionfundamentalpertama.AllComponent.CustomCard.CardKecil
import com.example.submissionfundamentalpertama.Api.ApiViewModel.DataDicodingEventViewModel
import com.example.submissionfundamentalpertama.Api.DataDicodingEvent
import com.example.submissionfundamentalpertama.NetworkMonitor.NetworkMonitor
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
fun PageFinished(
    searchResult: List<DataDicodingEvent>,
    navController: NavController
) {
    val dataDicodingEventViewModel: DataDicodingEventViewModel = viewModel()
    var dataDicodingEventFinished by remember { mutableStateOf(dataDicodingEventViewModel.eventFinished) }
    var isLoading by remember { mutableStateOf(true) }
    val createContext = LocalContext.current
    val netWorkMonitor = remember { NetworkMonitor(context = createContext) }
    var getError by remember { mutableStateOf("") }
    var isRefreshing by remember { mutableStateOf(false) }
    isLoading = dataDicodingEventFinished.isEmpty()

    fun fetchFinished() {
        isRefreshing = true
        if (searchResult.isEmpty()) {
            dataDicodingEventViewModel.fetchDataDicodingEvent(
                type = "Finished",
                onResult = { fetch ->
                    dataDicodingEventFinished = fetch
                    isLoading = false
                    isRefreshing= false
                },
                getError = {
                    getError = it
                    isLoading = false
                    isRefreshing= false
                }
            )
        } else {
            dataDicodingEventFinished = searchResult
            isRefreshing = false
        }
    }

    LaunchedEffect(searchResult) {
        netWorkMonitor.startNetworkCallback(
            onNetworkAvailable = {
                fetchFinished()
            },
        )
        fetchFinished()
    }

    if (getError.isNotBlank()) {
        Toast.makeText(createContext, getError, Toast.LENGTH_SHORT).show()
    }

    SwipeRefresh(
        state = rememberSwipeRefreshState(
            isRefreshing = isRefreshing
        ),
        onRefresh = {
            isRefreshing = true
            Toast.makeText(createContext, "on refresh", Toast.LENGTH_SHORT).show()
            fetchFinished()
            isRefreshing= false
        },
        content = {
            if (isLoading) {
                Column(
                    modifier = Modifier
                        .padding(top = 40.dp, bottom = 40.dp)
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    content = {
                        CircularProgressIndicator(color = Color(0xFFEE299B))
                        Text("Loading...", fontSize = 10.sp, modifier = Modifier.padding(5.dp))
                    }
                )
            } else {
                LazyVerticalStaggeredGrid(
                    columns = StaggeredGridCells.Fixed(2),
                    contentPadding = PaddingValues(10.dp),
                    verticalItemSpacing = 10.dp,
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    modifier = Modifier.fillMaxSize(),
                    content = {
                        items(
                            items = dataDicodingEventFinished,
                            itemContent = {
//                        CardBesar(
//                            id = it.id,
//                            catagory = it.category,
//                            img = it.imageLogo,
//                            name = it.name,
//                            ownerName = it.ownerName,
//                            summary = it.summary,
//                            beginTime = it.beginTime,
//                            endTime = it.endTime,
//                            onClick = { id ->
//                                Log.d("Get id card up coming", it.id.toString())
//                                Log.d("Get id card up coming", id.toString())
//                                navController.navigate("Detail/${id}")
//                            }
//                        )
                                CardKecil(
                                    img = it.imageLogo,
                                    name = it.name,
                                    ownerName = it.ownerName,
                                    catagory = it.category,
                                    onClick = {
                                        navController.navigate("Detail/${it.id}")
                                    }
                                )
                            }
                        )

                    }
                )
            }
        }
    )
}
