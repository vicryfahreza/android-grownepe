package com.vicry.grownepe.ui.screen.article.spesies.detailspecies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vicry.grownepe.model.nepenthes.StateNepenthes
import com.vicry.grownepe.model.repo.NepenthesRepository
import com.vicry.grownepe.ui.state.UIStatus
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class DetailSpeciesViewModel(
    private val repo: NepenthesRepository
) : ViewModel() {
    private val _uiStatus: MutableStateFlow<UIStatus<StateNepenthes>> =
        MutableStateFlow(UIStatus.Loading)
    val uiStatus: StateFlow<UIStatus<StateNepenthes>>
        get() = _uiStatus

    fun getSucculentById(nepenthesId: Long) {
        viewModelScope.launch {
            repo.getNepenthesById(nepenthesId)
                .catch {
                    _uiStatus.value = UIStatus.Error(it.message.toString())
                }
                .collect { detailSneakers ->
                    _uiStatus.value = UIStatus.Success(detailSneakers)
                }
        }
    }



}