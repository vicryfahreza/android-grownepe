package com.vicry.grownepe.ui.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.vicry.grownepe.ui.theme.GrowNepeTheme

@Composable
fun ArticleNepenthesRow(
    image: String,
    name: String,
    modifier: Modifier = Modifier,
){
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            AsyncImage(model = image,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(8.dp)
                    .size(150.dp)
                    .clip(CircleShape)
            )
        }

        Text(
            text = name,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Medium,
            fontSize = 18.sp,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(start = 8.dp, top = 8.dp, bottom = 8.dp)
        )
    }
}



@Composable
@Preview(showBackground = true)
fun DetailPreview() {
    GrowNepeTheme {
        ArticleNepenthesRow(image = "", name = "Mirabilis")
    }
}