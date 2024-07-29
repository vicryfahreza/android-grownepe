package com.vicry.grownepe.ui.screen.lms.detailLms

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vicry.grownepe.model.lms.StateLMS
import com.vicry.grownepe.model.repo.NepenthesRepository
import com.vicry.grownepe.ui.state.UIStatus
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class DetailLmsModel(
    private val repo: NepenthesRepository
) : ViewModel() {
    private val _uiStatus: MutableStateFlow<UIStatus<StateLMS>> =
        MutableStateFlow(UIStatus.Loading)
    val uiStatus: StateFlow<UIStatus<StateLMS>>
        get() = _uiStatus

    fun getLMSById(nepenthesId: Long) {
        viewModelScope.launch {
            repo.getLMSById(nepenthesId)
                .catch {
                    _uiStatus.value = UIStatus.Error(it.message.toString())
                }
                .collect { detailSneakers ->
                    _uiStatus.value = UIStatus.Success(detailSneakers)
                }
        }
    }



}