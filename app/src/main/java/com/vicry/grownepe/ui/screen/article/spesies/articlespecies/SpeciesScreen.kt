package com.vicry.grownepe.ui.screen.article.spesies.articlespecies

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.vicry.grownepe.model.nepenthes.StateNepenthes
import com.vicry.grownepe.model.repo.NepenthesInjection
import com.vicry.grownepe.ui.factory.ViewModelFactory
import com.vicry.grownepe.ui.navigation.ArticleNepenthesRow
import com.vicry.grownepe.ui.navigation.SearchNepenthes
import com.vicry.grownepe.ui.state.UIStatus

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SpeciesScreen(
    modifier: Modifier = Modifier,
    viewModel: SpeciesViewModel = viewModel(
        factory =  ViewModelFactory(NepenthesInjection.provideRepository())
    ),
    navigateToDetail: (Long) -> Unit,
) {
    viewModel.uistatus.collectAsState(initial = UIStatus.Loading).value.let { uistatus ->
        when (uistatus) {
            is UIStatus.Loading -> {
                viewModel.getAllNepenthes()
            }
            is UIStatus.Success -> {
                SpeciesContent(
                    state = uistatus.data,
                    modifier = modifier,
                    navigateToDetail = navigateToDetail,
                    viewModel = viewModel,
                )
            }
            is UIStatus.Error -> {}
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@ExperimentalMaterial3Api
@Composable
fun SpeciesContent(
    state: List<StateNepenthes>,
    navigateToDetail: (Long) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: SpeciesViewModel
) {
    Box {
        Column {
            val query by viewModel.query

            SearchNepenthes(
                query = query,
                onQueryChange = viewModel::search,
                modifier = Modifier.background(MaterialTheme.colorScheme.primary)
            )

            LazyColumn(
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = modifier
            ) {

                items(state) { data ->
                    ArticleNepenthesRow(
                        image = data.nepenthes.image,
                        name = data.nepenthes.name,
                        modifier = Modifier.clickable {
                            navigateToDetail(data.nepenthes.id)
                        })
                }

            }
        }
    }
}