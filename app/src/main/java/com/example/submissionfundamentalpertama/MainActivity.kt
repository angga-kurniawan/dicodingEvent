package com.example.submissionfundamentalpertama

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.submissionfundamentalpertama.AllComponent.CustomAppBar.AppBarrDetail
import com.example.submissionfundamentalpertama.AllComponent.CustomAppBar.FinishedAppBar
import com.example.submissionfundamentalpertama.AllComponent.CustomAppBar.HomeAppBar
import com.example.submissionfundamentalpertama.AllComponent.CustomAppBar.UpComingAppBar
import com.example.submissionfundamentalpertama.AllComponent.CustomBottomBar.BottomBar
import com.example.submissionfundamentalpertama.AllPage.PageDetail
import com.example.submissionfundamentalpertama.AllPage.PageFinished
import com.example.submissionfundamentalpertama.AllPage.PageHome
import com.example.submissionfundamentalpertama.AllPage.PageUpComing
import com.example.submissionfundamentalpertama.Api.ApiViewModel.DataDicodingEventViewModel
import com.example.submissionfundamentalpertama.Api.DataDicodingEvent
import com.example.submissionfundamentalpertama.ui.theme.SubmissionFundamentalPertamaTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SubmissionFundamentalPertamaTheme {
                val navControler = rememberNavController()
                var getRouteAppBar by remember {
                    mutableStateOf("")
                }
                var inputSearchFinished by remember {
                    mutableStateOf("")
                }
                val dataDicodingEventViewModel: DataDicodingEventViewModel = viewModel()
                var searchResult by remember {
                    mutableStateOf<List<DataDicodingEvent>>(emptyList())
                }
                val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
                val navBackEntry by navControler.currentBackStackEntryAsState()

                LaunchedEffect(navBackEntry?.destination?.route) {
                    getRouteAppBar = navBackEntry?.destination?.route ?: "Home"
                }

                Scaffold(
                    topBar = {
                        when (getRouteAppBar) {
                            "Home" -> HomeAppBar(scrollBehavior = scrollBehavior)
                            "Detail/{getId}" -> {
                                AppBarrDetail(navController = navControler)
                            }

                            "Up Coming" -> UpComingAppBar()
                            "Finished" -> FinishedAppBar(
                                inputSearch = inputSearchFinished,
                                onChangeInputSearch = {
                                    inputSearchFinished = it
                                },
                                onSearch = {
                                    dataDicodingEventViewModel.fetchSearchDataDicodingEvent(
                                        inputSearchFinished
                                    ) {
                                        searchResult = it
                                        Log.d("get keyword appbar finished",searchResult.toString())
                                    }
                                }
                            )

                            else -> {
                                HomeAppBar(scrollBehavior = scrollBehavior)
                            }
                        }
                    },
                    content = {
                        Column(
                            modifier = Modifier.padding(it),
                            content = {
                                NavHost(navController = navControler, startDestination = "Home") {
                                    composable("Home") {
                                        PageHome(
                                            navController = navControler,
                                            scrollBehavior = scrollBehavior
                                        )
                                    }
                                    composable("Up Coming") { PageUpComing(navController = navControler) }
                                    composable("Finished") {
                                        PageFinished(
                                            searchResult = searchResult,
                                            navController = navControler
                                        )
                                    }
                                    composable("Detail/{getId}") { backStackEntry ->
                                        val eventId =
                                            backStackEntry.arguments?.getString("getId")?.toInt()
                                        eventId?.let { set ->
                                            PageDetail(getId = set)
                                            Log.d("setId", set.toString())
                                        }
                                    }
                                }
                            }
                        )
                    },
                    bottomBar = {
                        if (navBackEntry?.destination?.route != "Detail/{getId}") {
                            BottomBar(
                                navController = navControler,
                                getTopBar = { getRouteAppBar = it }
                            )
                        }
                    }
                )
            }
        }
    }
}
