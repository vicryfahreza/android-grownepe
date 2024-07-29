package com.vicry.grownepe.ui.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.vicry.grownepe.ui.theme.GrowNepeTheme

@Composable
fun LmsNepenthesRow (
    name: String,
    image: String,
    modifier: Modifier = Modifier
){

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        Column {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {
                Box(
                    Modifier
                        .height(120.dp)
                        .fillMaxWidth()
                ) {
                    AsyncImage(
                        model = image,
                        modifier = Modifier.fillMaxSize(),
                        contentDescription = "Background Image",
                        contentScale = ContentScale.Crop
                    )
                    Text(
                        text = name,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        modifier = modifier.padding(16.dp)
                    )
                }

            }
        }

    }


}

@Composable
@Preview(showBackground = true)
fun LmsPreview() {
    GrowNepeTheme {
        LmsNepenthesRow(
            name = "Tutorial Media Tanam",
            image = "",
        )
    }
}