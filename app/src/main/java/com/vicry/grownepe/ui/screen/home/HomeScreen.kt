package com.vicry.grownepe.ui.screen.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.vicry.grownepe.R
import com.vicry.grownepe.model.home.StateLowLandNepe
import com.vicry.grownepe.model.home.StateSoil
import com.vicry.grownepe.model.repo.NepenthesInjection
import com.vicry.grownepe.ui.factory.ViewModelFactory
import com.vicry.grownepe.ui.navigation.LowLandRow
import com.vicry.grownepe.ui.navigation.SoilRow
import com.vicry.grownepe.ui.state.UIStatus

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(
        factory =  ViewModelFactory(NepenthesInjection.provideRepository())
    ),
) {
    viewModel.uistatus.collectAsState(initial = UIStatus.Loading).value.let { uistatus ->
        when (uistatus) {
            is UIStatus.Loading -> {
                viewModel.getAllLowLand()
            }
            is UIStatus.Success -> {

                Box(
                    modifier = modifier.fillMaxSize(),
                ) {
                    Column(
                        modifier = Modifier.verticalScroll(
                            rememberScrollState()
                        )
                    ){
                        SectionTopBar()
                        Banner()
                        SectionNepenthes()
                        LowLandContent(
                            state = uistatus.data,
                            modifier = modifier,
                        )
                        SectionSoil()
                        viewModel.uistatusSoil.collectAsState(initial = UIStatus.Loading).value.let { uistatusSoil ->
                            when (uistatusSoil) {
                                is UIStatus.Loading -> {
                                    viewModel.getAllSoil()
                                }
                                is UIStatus.Success -> {
                                    SoilContent(
                                        state = uistatusSoil.data,
                                        modifier = modifier
                                    )
                                }
                                is UIStatus.Error -> {}
                            }
                        }
                    }
                }
            }
            is UIStatus.Error -> {}
        }
    }
}

@Composable
fun SectionTopBar(modifier: Modifier = Modifier) {
    Text(
        text = stringResource(R.string.home_top_bar),
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp,
        color = Color.Black,
        modifier = modifier
            .padding(10.dp)
            .fillMaxWidth()
    )
}

@Composable
fun Banner(modifier: Modifier = Modifier){
    AsyncImage(model = stringResource(R.string.article_natural_hybrid_banner),
        contentDescription = null,
        contentScale = ContentScale.Fit,
        modifier = modifier
            .fillMaxWidth()
    )
}

@Composable
fun SectionSoil(modifier: Modifier = Modifier ) {
    Row {
        Text(
            text = stringResource(R.string.home_label2),
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            color = Color.Black,
            modifier = modifier
                .padding(10.dp)
                .fillMaxWidth()
                .weight(1f)
        )
    }
}

@Composable
fun SectionNepenthes(modifier: Modifier = Modifier) {
    Row {
        Text(
            text = stringResource(R.string.home_label1),
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            color = Color.Black,
            modifier = modifier
                .padding(10.dp)
                .fillMaxWidth()
                .weight(1f)
        )
    }
}

@Composable
fun LowLandContent(
    state: List<StateLowLandNepe>,
    modifier: Modifier = Modifier
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        modifier = modifier
    ) {
        items(state) {data ->
            LowLandRow(
                name = data.lowLand.name,
                image = data.lowLand.image,
                type = data.lowLand.type,
            )
        }
    }
}

@Composable
fun SoilContent(
    state: List<StateSoil>,
    modifier: Modifier = Modifier
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        modifier = modifier
    ) {
        items(state) {data ->
            SoilRow(
                name = data.soil.name,
                image = data.soil.image
            )
        }
    }
}
