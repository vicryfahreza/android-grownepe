package com.vicry.grownepe.ui.screen.home

import androidx.compose.foundation.clickable
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
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
    sectionTopBar: String,
    banner: String,
    sectionSoil: String,
    sectionNepenthes: String,
    navigateToSoil: (Long) -> Unit,
    navigateToLowLandNepe: (Long) -> Unit,
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
                        SectionTopBar(modifier = modifier, sectionTopBar = sectionTopBar)
                        Banner(modifier = modifier, banner = banner)
                        SectionNepenthes(modifier = modifier, sectionNepenthes = sectionNepenthes)
                        LowLandContent(
                            state = uistatus.data,
                            modifier = modifier,
                            navigateToLowLandNepe = navigateToLowLandNepe
                        )
                        SectionSoil(modifier = modifier, sectionSoil = sectionSoil)
                        viewModel.uistatusSoil.collectAsState(initial = UIStatus.Loading).value.let { uistatusSoil ->
                            when (uistatusSoil) {
                                is UIStatus.Loading -> {
                                    viewModel.getAllSoil()
                                }
                                is UIStatus.Success -> {
                                    SoilContent(
                                        state = uistatusSoil.data,
                                        modifier = modifier,
                                        navigateToSoil = navigateToSoil
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
fun SectionTopBar(modifier: Modifier = Modifier, sectionTopBar: String) {
    Text(
        text = sectionTopBar,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp,
        modifier = modifier
            .padding(10.dp)
            .fillMaxWidth()
    )
}

@Composable
fun Banner(modifier: Modifier = Modifier, banner: String){
    AsyncImage(model = banner,
        contentDescription = null,
        contentScale = ContentScale.Fit,
        modifier = modifier
            .fillMaxWidth()
    )
}

@Composable
fun SectionSoil(modifier: Modifier = Modifier , sectionSoil: String) {
    Row {
        Text(
            text = sectionSoil,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            modifier = modifier
                .padding(10.dp)
                .fillMaxWidth()
                .weight(1f)
        )
    }
}

@Composable
fun SectionNepenthes(modifier: Modifier = Modifier, sectionNepenthes: String) {
    Row {
        Text(
            text = sectionNepenthes,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
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
    navigateToLowLandNepe: (Long) -> Unit,
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
                modifier = Modifier.clickable {
                    navigateToLowLandNepe(data.lowLand.id)
                }
            )
        }
    }
}

@Composable
fun SoilContent(
    state: List<StateSoil>,
    navigateToSoil: (Long) -> Unit,
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
                image = data.soil.image,
                modifier = Modifier.clickable {
                    navigateToSoil(data.soil.id)
                })
        }
    }
}
