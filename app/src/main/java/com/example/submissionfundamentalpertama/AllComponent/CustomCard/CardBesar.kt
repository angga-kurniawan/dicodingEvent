package com.example.submissionfundamentalpertama.AllComponent.CustomCard

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.submissionfundamentalpertama.Adapter.HitungHari

@SuppressLint("InvalidColorHexValue")
@Composable
fun CardBesar(
    id: Int,
    img: String,
    catagory: String,
    name: String,
    ownerName: String,
    summary: String,
    beginTime: String,
    endTime: String,
    onClick: (Int) -> Unit,
) {
    Card(
        modifier = Modifier.shadow(
            10.dp,
            RoundedCornerShape(10.dp)
        ).clickable {
            onClick(id)
        },
        content = {
            Image(
                painter = rememberAsyncImagePainter(img),
                contentScale = ContentScale.FillHeight,
                contentDescription = "img url",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp)
            )
            Box(
                modifier = Modifier
                    .background(Color.White)
                    .fillMaxSize(),
                content = {
                    Column(
                        modifier = Modifier.padding(10.dp),
                        content = {
                            Box(
                                modifier = Modifier
                                    .padding(top = 10.dp, bottom = 10.dp)
                                    .border(
                                        width = 2.dp,
                                        shape = RoundedCornerShape(5.dp),
                                        color = Color(0xFF565656)
                                    ),
                                content = {
                                    Text(
                                        text = catagory,
                                        modifier = Modifier.padding(
                                            top = 0.dp,
                                            bottom = 0.dp,
                                            start = 10.dp,
                                            end = 10.dp
                                        ),
                                        color = Color(0xFF565656),
                                        fontSize = 10.sp
                                    )
                                }
                            )
                            Text(
                                text = name,
                                modifier = Modifier.fillMaxWidth(),
                                color = Color(
                                    0xFFEE299B
                                )
                            )
                            Row(
                                content = {
                                    Text(text = "oleh: ", fontSize = 10.sp)
                                    Text(
                                        text = ownerName,
                                        fontSize = 10.sp,
                                        color = Color(0xFF565656)
                                    )
                                }
                            )
                            Text(
                                text = summary,
                                modifier = Modifier.padding(top = 5.dp, bottom = 20.dp)
                            )

                        }
                    )
                }
            )
            HorizontalDivider()
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .background(Color(0xF0F8F8F8)),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                content = {
                    Text(
                        text = " ${
                            HitungHari(
                                beginTime = beginTime,
                                endTime = endTime
                            )
                        }",
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                    )
                }
            )
        }
    )
}

@Preview
@Composable
private fun CardUpComingPrev() {
    CardBesar(
        id = 0,
        img = "img",
        catagory = "catagory",
        name = "name",
        ownerName = "owner name",
        summary = "summery",
        beginTime = "",
        endTime = "",
        onClick = {}
    )
}














