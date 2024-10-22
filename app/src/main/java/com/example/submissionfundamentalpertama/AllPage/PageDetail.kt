package com.example.submissionfundamentalpertama.AllPage

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.webkit.WebView
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.example.submissionfundamentalpertama.Api.ApiViewModel.DataDicodingEventViewModel
import com.example.submissionfundamentalpertama.Api.DataDicodingEvent
import com.example.submissionfundamentalpertama.NetworkMonitor.NetworkMonitor
import com.example.submissionfundamentalpertama.Room.FavoriteViewModel
import com.example.submissionfundamentalpertama.Room.Table

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun PageDetail(
    getId: Int
) {
    val favoriteViewModel: FavoriteViewModel = viewModel()
    val dataDicodingEventViewModel: DataDicodingEventViewModel = viewModel()
    var dataDicodingEventDetail by remember { mutableStateOf<DataDicodingEvent?>(null) }
    val createContext = LocalContext.current
    var isLoading by remember { mutableStateOf(true) }
    val netWorkMonitor = remember { NetworkMonitor(context = createContext) }
    val dataDicodingEventDetailCache =
        dataDicodingEventViewModel.eventDetailCache.find { it.id == getId }
//    val favoriteItem = favoriteViewModel.getFavoriteById(getId)
    var isFavorite by remember { mutableStateOf(false) }


    if (dataDicodingEventDetailCache != null) {
        dataDicodingEventDetail = dataDicodingEventDetailCache
        isLoading = false
    } else {
        isLoading = true
    }

    Log.d("get id bos qu", getId.toString())
    Log.d("get isloading bos qu", isLoading.toString())
    Log.d("get chace bos qu", dataDicodingEventDetailCache.toString())
    fun fetchPagedetail() {
        dataDicodingEventViewModel.fetchDetailDicodingEvent(
            getId = getId,
            onResult = { fetch ->
                dataDicodingEventDetail = fetch
                Log.d("dataDicodingEventDetail", dataDicodingEventDetail.toString())
                Log.d(
                    "dataDicodingEventDetail descripis",
                    dataDicodingEventDetail!!.description
                )
                isLoading = false
            }
        )
    }
    LaunchedEffect(getId) {
        favoriteViewModel.isItemFavorite(getId) { isFavoriteResult ->
            isFavorite = isFavoriteResult
        }
    }

    LaunchedEffect(getId) {
        netWorkMonitor.startNetworkCallback(
            onNetworkAvailable = {
                fetchPagedetail()
            },

            )
        if (dataDicodingEventDetailCache == null) {
            fetchPagedetail()
        }
    }




    Log.d("get desc", dataDicodingEventDetail?.description.toString())
    if (isLoading) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            content = {
                CircularProgressIndicator(color = Color(0xFFEE299B))
                Text(
                    "Loading...",
                    fontSize = 10.sp,
                    modifier = Modifier.padding(5.dp)
                )
            }
        )
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            content = {
                dataDicodingEventDetail?.let { data ->
                    Image(
                        painter = rememberAsyncImagePainter(data.imageLogo),
                        contentDescription = "image",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .padding(10.dp)
                            .shadow(5.dp, RoundedCornerShape(10.dp))
                            .fillMaxWidth()
                            .height(300.dp),
                    )
                    FloatingActionButton(
                        modifier = Modifier
                            .align(Alignment.End)
                            .padding(end = 40.dp)
                            .offset(y = (-40).dp)
                            .clip(CircleShape),
                        onClick = {
                            isFavorite = !isFavorite
                                val table = Table(
                                   id = data.id,
                                    img = data.imageLogo,
                                    catagory = data.category,
                                    ownerName = data.ownerName,
                                    summary = data.summary,
                                    beginTime = data.beginTime,
                                    endTime = data.endTime,
                                    name = data.name,
                                    isFavorite = isFavorite
                                )
                            if (isFavorite) {
                                favoriteViewModel.addFavorite(table = table)
                            } else {
                                favoriteViewModel.deleteFavorite(table = table)
                            }
                        },
                        content = {
                            Icon(
                                imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder ,
                                contentDescription = "faforite"
                            )
                        }
                    )
                    Column(
                        modifier = Modifier.offset(y = (-60).dp),
                        content = {
                            Box(
                                modifier = Modifier
                                    .padding(start = 10.dp, bottom = 10.dp, top = 20.dp)
                                    .border(
                                        width = 2.dp,
                                        shape = RoundedCornerShape(5.dp),
                                        color = Color(0xFF565656)
                                    ),
                                content = {
                                    Text(
                                        text = data.category,
                                        modifier = Modifier.padding(
                                            top = 0.dp,
                                            bottom = 0.dp,
                                            start = 10.dp,
                                            end = 10.dp
                                        ),
                                        color = MaterialTheme.colorScheme.onPrimary,
                                        fontSize = 10.sp
                                    )
                                }
                            )
                            Text(
                                text = data.name,
                                fontSize = 30.sp,
                                modifier = Modifier.padding(start = 10.dp, bottom = 10.dp),
                                lineHeight = 40.sp,
                                color = MaterialTheme.colorScheme.onPrimary
                            )

                            Text(
                                text = "Diselenggarakan oleh: ${data.ownerName}",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Light,
                                color = MaterialTheme.colorScheme.onPrimary,
                                modifier = Modifier.padding(start = 10.dp, bottom = 20.dp)
                            )

                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(150.dp),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally,
                                content = {
                                    Text(
                                        text = "Terbuka Hingga:",
                                        color = MaterialTheme.colorScheme.onPrimary
                                    )
                                    Text(
                                        text = data.beginTime,
                                        fontWeight = FontWeight.Bold,
                                        color = MaterialTheme.colorScheme.onPrimary
                                    )
                                    Text(
                                        text = "Sisa Kuota:",
                                        color = MaterialTheme.colorScheme.onPrimary
                                    )
                                    Text(
                                        text = "${data.quota - data.registrants}",
                                        fontWeight = FontWeight.Bold,
                                        color = MaterialTheme.colorScheme.onPrimary
                                    )
                                    Text(
                                        text = "Registrant:",
                                        color = MaterialTheme.colorScheme.onPrimary
                                    )
                                    Text(
                                        text = data.registrants.toString(),
                                        fontWeight = FontWeight.Bold,
                                        color = MaterialTheme.colorScheme.onPrimary
                                    )
                                    Log.d("get registrant", data.registrants.toString())
                                }
                            )

                            Text(
                                text = "Deskripsi",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(start = 20.dp),
                                color = MaterialTheme.colorScheme.onPrimary
                            )


//                    AndroidView(
//                        modifier = Modifier,
//                        factory = { context ->
//                            TextView(context).apply {
//                                text = Html.fromHtml(data.description,Html.FROM_HTML_MODE_COMPACT)
//
//                            }
//                        }
//                    )
                            val isLightTheme = MaterialTheme.colorScheme.background == Color.White
                            AndroidView(
                                factory = {
                                    WebView(it).apply {
                                        settings.javaScriptEnabled = true
                                        setBackgroundColor(Color.Transparent.toArgb())
                                        val textColorWebView =
                                            if (isLightTheme) "#000000" else "#FFFFFF"
                                        val tableBorderColor =
                                            if (isLightTheme) "#cccccc" else "#FFFFFF"
                                        loadDataWithBaseURL(
                                            null,
                                            """
                                     <html>
                                        <head>
                                            <style>
                                                body {
                                                    font-family: 'Roboto', sans-serif;
                                                    margin: 0;
                                                    padding: 10px;
                                                    background-color: rgba(249, 249, 249, 0);
                                                    color: $textColorWebView;
                                                }
                                                p {
                                                    font-size: 14px;
                                                    line-height: 1.6;
                                                    margin-bottom: 10px;
                                                }
                                                img {
                                                    max-width: 100%;
                                                    height: auto;
                                                    border-radius: 8px;
                                                }
                                                ul, ol {
                                                    margin-left: 20px;
                                                }
                                                table {
                                                    width: 100%;
                                                    border-collapse: collapse;
                                                }
                                                th, td {
                                                    border: 1px solid $tableBorderColor; /* Menggunakan warna garis tabel berdasarkan tema */
                                                    padding: 8px;
                                                    text-align: left;
                                                    color: $textColorWebView; /* Menggunakan warna teks untuk tabel berdasarkan tema */
                                                }
                                                .note {
                                                    color: #E25241;
                                                    font-weight: bold;
                                                    margin-top: 10px;
                                                }
                                                hr {
                                                    border: 0;
                                                    border-top: 1px solid #cccccc;
                                                    margin: 20px 0;
                                                }
                                            </style>
                                        </head>
                                        <body>
                                             ${data.description}
                                        </body>
                                    </html>
                                """.trimIndent(),
                                            "text/html; charset=utf-8",
                                            "UTF-8",
                                            null
                                        )
                                    }
                                },
                                modifier = Modifier
                                    .padding(10.dp)
                                    .fillMaxSize()
                                    .background(Color.Transparent)
                            )

                            Button(
                                modifier = Modifier
                                    .padding(
                                        start = 20.dp,
                                        end = 20.dp,
                                        top = 20.dp,
                                        bottom = 50.dp
                                    )
                                    .fillMaxWidth()
                                    .height(60.dp)
                                    .border(2.dp, Color(0xFFEE299B), RoundedCornerShape(10.dp)),
                                colors = ButtonDefaults.buttonColors(
                                    Color.Transparent
                                ),
                                onClick = {
                                    val intent =
                                        Intent(Intent.ACTION_VIEW, Uri.parse(data.link))
                                    createContext.startActivity(intent)
                                },
                                content = {
                                    Text(text = "Registrasi", color = Color(0xFFEE299B))
                                }
                            )
                        }
                    )

                }
            }

        )
    }

}

@Preview
@Composable
private fun tampilkan() {
    PageDetail(1)
}

















