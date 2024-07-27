package com.vicry.grownepe.ui.screen.article.cultivar.detailcultivar

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vicry.grownepe.model.nepenthes.StateCultivar
import com.vicry.grownepe.model.repo.NepenthesRepository
import com.vicry.grownepe.ui.state.UIStatus
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class DetailCultivarViewModel (
    private val repo: NepenthesRepository
) : ViewModel() {
    private val _uiStatus: MutableStateFlow<UIStatus<StateCultivar>> =
        MutableStateFlow(UIStatus.Loading)
    val uiStatus: StateFlow<UIStatus<StateCultivar>>
        get() = _uiStatus

    fun getSucculentById(nepenthesId: Long) {
        viewModelScope.launch {
            repo.getCultivarById(nepenthesId)
                .catch {
                    _uiStatus.value = UIStatus.Error(it.message.toString())
                }
                .collect { detailSneakers ->
                    _uiStatus.value = UIStatus.Success(detailSneakers)
                }
        }
    }



}