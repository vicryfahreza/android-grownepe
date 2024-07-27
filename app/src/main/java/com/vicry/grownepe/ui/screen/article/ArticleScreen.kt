package com.vicry.grownepe.ui.screen.article

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
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
fun ArticleScreen(
    modifier: Modifier = Modifier,
    speciesImage: String,
    naturalHybridImage: String,
    cultivarImage: String,
    speciesLabel: String,
    naturalLabel: String,
    cultivarLabel: String,
    navigateToSpesies: (String) -> Unit,
    navigateToCultivar: (String) -> Unit,
    navigateToNaturalHybrid: (String) -> Unit,
    ) {
    Box{
        Column(
            modifier = Modifier.verticalScroll(
                rememberScrollState()
            )
        ) {

            AsyncImage(
                model = speciesImage,
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = modifier
                    .padding(10.dp)
                    .width(400.dp)
                    .height(150.dp)
            )

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = speciesLabel,
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                    color = Color.Black,
                    modifier = modifier
                        .padding(10.dp)
                        .fillMaxWidth()
                        .weight(1f)
                        .padding(start = 8.dp)
                )
            }

            Button(
                onClick = { navigateToSpesies("species") },
                modifier = Modifier
                    .padding(15.dp)
                    .align(Alignment.CenterHorizontally)
                    .width(400.dp)
                ){
                Text(
                    text = "Cari Species Nepenthes",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    )
            }

            AsyncImage(
                model = cultivarImage,
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = modifier
                    .padding(10.dp)
                    .width(400.dp)
                    .height(150.dp)
            )

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = cultivarLabel,
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                    color = Color.Black,
                    modifier = modifier
                        .padding(10.dp)
                        .fillMaxWidth()
                        .weight(1f)
                        .padding(start = 8.dp)
                )
            }

            Button(onClick = { navigateToCultivar("cultivar") },
                modifier = Modifier
                    .padding(15.dp)
                    .align(Alignment.CenterHorizontally)
                    .width(400.dp)
                ){
                Text(
                    text = "Cari Kultivar Nepenthes",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,)
            }

            AsyncImage(
                model = naturalHybridImage,
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = modifier
                    .padding(10.dp)
                    .width(400.dp)
                    .height(150.dp)
            )

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = naturalLabel,
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                    color = Color.Black,
                    modifier = modifier
                        .padding(10.dp)
                        .fillMaxWidth()
                        .weight(1f)
                        .padding(start = 8.dp)
                )
            }

            Button(onClick = { navigateToNaturalHybrid("hybrid") },
                modifier = Modifier
                    .padding(15.dp)
                    .align(Alignment.CenterHorizontally)
                    .width(400.dp)
                ){
                Text(text = "Cari Hybrid Alam Nepenthes")
            }

        }

    }
}

@Composable
@Preview(showBackground = true)
fun DetailPreview() {
    GrowNepeTheme {
        ArticleScreen(
            speciesImage = "",
            naturalHybridImage = "",
            cultivarImage = "",
            speciesLabel = "Species",
            naturalLabel = "Hybrid Alam",
            cultivarLabel = "Cultivar",
            navigateToSpesies = {},
            navigateToCultivar= {},
            navigateToNaturalHybrid = {},
        )
    }
}
