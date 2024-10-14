package com.example.submissionfundamentalpertama.AllComponent.CustomCard

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter

@Composable
fun CardKecil(
    img: String,
    catagory: String,
    name: String,
    ownerName: String,
    onClick: () -> Unit
) {
    Card(
        colors = CardDefaults.cardColors(
            Color.White
        ),
        modifier = Modifier
            .width(200.dp)
            .shadow(
                5.dp,
                ambientColor = Color(0xFF00B0FF),
                spotColor = Color(0xFF00B0FF),
                shape = RoundedCornerShape(10.dp)
            )
            .clickable {
                onClick()
            },
        content = {
            Image(
                painter = rememberAsyncImagePainter(img),
                contentDescription = "img",
                modifier = Modifier.height(200.dp),
                contentScale = ContentScale.Crop
            )
            Box(
                modifier = Modifier.fillMaxWidth(),
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
                        }
                    )
                }
            )
        }
    )
}

@Preview
@Composable
private fun CardFinishedPrev() {
    CardKecil("", "","","") {}
}















