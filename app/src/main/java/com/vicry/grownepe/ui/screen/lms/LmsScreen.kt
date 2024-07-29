package com.vicry.grownepe.ui.screen.lms

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.vicry.grownepe.model.lms.StateLMS
import com.vicry.grownepe.model.repo.NepenthesInjection
import com.vicry.grownepe.ui.factory.ViewModelFactory
import com.vicry.grownepe.ui.navigation.LmsNepenthesRow
import com.vicry.grownepe.ui.state.UIStatus
import com.vicry.grownepe.ui.theme.GrowNepeTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LmsScreen(
    modifier: Modifier = Modifier,
    viewModel: LmsViewModel = viewModel(
        factory =  ViewModelFactory(NepenthesInjection.provideRepository())
    ),
    navigateToModule: (Long) -> Unit,
) {
    viewModel.uistatus.collectAsState(initial = UIStatus.Loading).value.let { uistatus ->
        when (uistatus) {
            is UIStatus.Loading -> {
                viewModel.getAllLMS()
            }
            is UIStatus.Success -> {
                LmsContent(
                    state = uistatus.data,
                    modifier = modifier,
                    navigateToModule = navigateToModule,
                )
            }
            is UIStatus.Error -> {}
        }
    }

}

@ExperimentalMaterial3Api
@Composable
fun LmsContent(
    state: List<StateLMS>,
    navigateToModule: (Long) -> Unit,
    modifier: Modifier = Modifier,
) {
    Box {
        Column {
            LazyColumn(
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = modifier
            ) {

                items(state) { data ->
                    LmsNepenthesRow(
                        name = data.lms.title,
                        image = data.lms.imageBackground,
                        modifier = Modifier.clickable {
                            navigateToModule(data.lms.id)
                        }
                    )
                }
            }
        }
    }
}


@Composable
@Preview(showBackground = true)
fun DetailPreview() {
    GrowNepeTheme {
        LmsScreen(navigateToModule = {})
    }
}