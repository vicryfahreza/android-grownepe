package com.vicry.grownepe.ui.screen.article.info

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

@Composable
fun InfoSpeciesScreen(
    modifier: Modifier = Modifier,
    infoSpeciesImage: String,
    infoSpeciesText: String,
    infoIUCNImage: String,
    infoIUCNText: String,
    infoIUCNDescription: String,
    speciesButton: String,
    navigateToSpesies: (String) -> Unit,
    ){
    Box{
        Column(
            modifier = Modifier.verticalScroll(
                rememberScrollState()
            )
        ) {
            AsyncImage(
                model = infoSpeciesImage,
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = modifier
                    .padding(end = 10.dp, start = 10.dp)
                    .fillMaxWidth()
            )

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = infoSpeciesText,
                    textAlign = TextAlign.Justify,
                    fontSize = 18.sp,
                    modifier = modifier
                        .padding(10.dp)
                        .fillMaxWidth()
                        .weight(1f)
                        .padding(start = 8.dp)
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = infoIUCNText,
                    textAlign = TextAlign.Justify,
                    fontSize = 26.sp,
                    modifier = modifier
                        .padding(10.dp)
                        .fillMaxWidth()
                        .weight(1f)
                        .padding(start = 8.dp)
                )
            }

            AsyncImage(
                model = infoIUCNImage,
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = modifier
                    .padding(end = 10.dp, start = 10.dp)
                    .fillMaxWidth()
            )

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = infoIUCNDescription,
                    textAlign = TextAlign.Justify,
                    fontSize = 18.sp,
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
                    text = speciesButton,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                )
            }
        }
    }
}