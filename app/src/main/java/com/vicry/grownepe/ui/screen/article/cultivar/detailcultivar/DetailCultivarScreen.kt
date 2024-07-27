package com.vicry.grownepe.ui.screen.article.cultivar.detailcultivar

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.vicry.grownepe.model.repo.NepenthesInjection
import com.vicry.grownepe.ui.factory.ViewModelFactory
import com.vicry.grownepe.ui.state.UIStatus
import com.vicry.grownepe.ui.theme.GrowNepeTheme

@Composable
fun DetailCultivarScreen(
    nepenthesId: Long,
    modifier: Modifier = Modifier,
    viewModel: DetailCultivarViewModel = viewModel(
        factory = ViewModelFactory(
            NepenthesInjection.provideRepository()
        )
    )
) {
    Scaffold { padding ->
        Box(modifier = modifier.padding(padding)) {
            viewModel.uiStatus.collectAsState(initial = UIStatus.Loading).value.let { uiStatus ->
                when (uiStatus) {
                    is UIStatus.Loading -> {
                        viewModel.getSucculentById(nepenthesId)
                    }
                    is UIStatus.Success -> {
                        val detail = uiStatus.data
                        DetailContent(
                            detail.nepenthes.image,
                            detail.nepenthes.name,
                            detail.nepenthes.description,
                            detail.nepenthes.bestSoil
                        )
                    }
                    is UIStatus.Error -> {}
                }
            }
        }
    }
}

@Composable
fun DetailContent(
    image: String,
    name: String,
    description: String,
    soil: String,
    modifier: Modifier = Modifier,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(model = image,
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = modifier
                .padding(10.dp)
                .size(200.dp)
                .clip(CircleShape)
        )

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = name,
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

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = description,
                fontWeight = FontWeight.Normal,
                fontSize = 18.sp,
                textAlign = TextAlign.Justify,
                color = Color.Black,
                modifier = modifier
                    .padding(10.dp)
                    .weight(1f)
                    .padding(start = 8.dp)
            )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Soil Recomendation",
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

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = soil,
                fontWeight = FontWeight.Normal,
                fontSize = 18.sp,
                textAlign = TextAlign.Justify,
                color = Color.Black,
                modifier = modifier
                    .padding(10.dp)
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(start = 8.dp)
            )
        }

    }



}

@Composable
@Preview(showBackground = true)
fun DetailPreview() {
    GrowNepeTheme {
        DetailContent(
            image = "",
            name = "Echeveria",
            description = "lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsum",
            soil = "lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsum",
        )
    }
}