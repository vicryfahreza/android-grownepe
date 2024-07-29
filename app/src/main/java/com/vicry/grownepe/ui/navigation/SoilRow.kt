package com.vicry.grownepe.ui.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

@Composable
fun SoilRow (
    name: String,
    image: String,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = image,
            contentDescription = null,
            modifier = Modifier
                .requiredSize(80.dp)
                .clip(CircleShape)
        )
        Text(
            text = name,
            maxLines = 2,
            fontSize = 10.sp,
            modifier = Modifier.paddingFromBaseline(top = 16.dp, bottom = 8.dp).width(140.dp)
        )
    }
}