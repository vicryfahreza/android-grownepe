package com.vicry.grownepe.ui.screen.article.spesies.detailspecies

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
fun DetailSpeciesScreen(
    nepenthesId: Long,
    modifier: Modifier = Modifier,
    labelDesc1: String,
    labelDesc2: String,
    labelSoil: String,
    labelEnv: String,
    labelHeight: String,
    labelIUCN: String,
    labelDistribution: String,
    viewModel: DetailSpeciesViewModel = viewModel(
        factory = ViewModelFactory(
            NepenthesInjection.provideRepository()
        )
    )
) {
    Scaffold { padding ->
        Box(modifier = modifier.padding(padding)) {
            Column(modifier = Modifier.verticalScroll(
                    rememberScrollState())){
                viewModel.uiStatus.collectAsState(initial = UIStatus.Loading).value.let { uiStatus ->
                    when (uiStatus) {
                        is UIStatus.Loading -> {
                            viewModel.getSucculentById(nepenthesId)
                        }
                        is UIStatus.Success -> {
                            val detail = uiStatus.data
                            DetailContent(
                                detail.nepenthes.name,
                                detail.nepenthes.image,
                                detail.nepenthes.imageSource,
                                detail.nepenthes.description,
                                detail.nepenthes.bestSoil,
                                detail.nepenthes.bestEnvironment,
                                detail.nepenthes.description2,
                                detail.nepenthes.height,
                                detail.nepenthes.statusIUCN,
                                detail.nepenthes.distribution,
                                labelDesc1,
                                labelDesc2,
                                labelSoil,
                                labelEnv,
                                labelHeight,
                                labelIUCN,
                                labelDistribution,
                            )
                        }
                        is UIStatus.Error -> {}
                    }
                }
            }
        }
    }
}

@Composable
fun DetailContent(
    name: String,
    image: String,
    imageSource: String,
    description: String,
    bestSoil: String,
    bestEnv: String,
    description2: String,
    height: String,
    statusIUCN: String,
    distribution: String,
    labelDesc1: String,
    labelDesc2: String,
    labelSoil: String,
    labelEnv: String,
    labelHeight: String,
    labelIUCN: String,
    labelDistribution: String,
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
                .width(400.dp)
                .height(200.dp)
        )

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = imageSource,
                fontWeight = FontWeight.Bold,
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
                text = name,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
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
                text = labelDesc1,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
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
                text = labelIUCN,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
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
            AsyncImage(model = statusIUCN,
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = modifier
                    .padding(10.dp)
                    .width(100.dp)
                    .height(100.dp)
            )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = labelDesc2,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
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
                text = description2,
                fontWeight = FontWeight.Normal,
                fontSize = 18.sp,
                textAlign = TextAlign.Justify,
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
                text = labelDistribution,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
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
                text = distribution,
                fontWeight = FontWeight.Normal,
                fontSize = 18.sp,
                textAlign = TextAlign.Justify,
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
                text = labelSoil,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
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
                text = bestSoil,
                fontWeight = FontWeight.Normal,
                fontSize = 18.sp,
                textAlign = TextAlign.Justify,
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
                text = labelEnv,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
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
                text = bestEnv,
                fontWeight = FontWeight.Normal,
                fontSize = 18.sp,
                textAlign = TextAlign.Justify,
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
                text = labelHeight,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
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
                text = height,
                fontWeight = FontWeight.Normal,
                fontSize = 18.sp,
                textAlign = TextAlign.Justify,
                modifier = modifier
                    .padding(10.dp)
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
            name = "abalata",
            image = "https://res.cloudinary.com/ddmaxq4xx/image/upload/v1735874967/Nepenthes/article-species/abalata_kaw86g.jpg",
            imageSource =  "Saya",
            description =  "Nepenthes ini berasal dari kepulauan Sumatera hingga borneo",
            bestSoil =  "Cocopeat menjadi pilihan yang baik",
            bestEnv = "Low Land hingga Ultra Highland",
            description2 = "Nepenthes ini berada di lingkungan rawa rawa basah dekat dengan laut",
            height = "0 sampai 100 Meter",
            statusIUCN = "https://res.cloudinary.com/ddmaxq4xx/image/upload/v1737265210/Nepenthes/IUCN/EN_hp9vpo.png",
            distribution = "Filipina",
            labelDesc1 = "Trivia",
            labelDesc2 = "Habitat",
            labelSoil = "Media Tanam",
            labelEnv = "Kondisi Lingkungan",
            labelHeight = "Ketinggian",
            labelIUCN = "Status IUCN",
            labelDistribution = "Persebaran",
        )
    }
}