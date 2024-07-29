package com.vicry.grownepe.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vicry.grownepe.model.home.StateLowLandNepe
import com.vicry.grownepe.model.home.StateSoil
import com.vicry.grownepe.model.repo.NepenthesRepository
import com.vicry.grownepe.ui.state.UIStatus
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeViewModel (
    private val repository: NepenthesRepository
) : ViewModel() {
    private val _uistatus: MutableStateFlow<UIStatus<List<StateLowLandNepe>>> = MutableStateFlow(
        UIStatus.Loading)
    val uistatus: StateFlow<UIStatus<List<StateLowLandNepe>>>
        get() = _uistatus

    private val _uistatusSoil: MutableStateFlow<UIStatus<List<StateSoil>>> = MutableStateFlow(
        UIStatus.Loading)
    val uistatusSoil: StateFlow<UIStatus<List<StateSoil>>>
        get() = _uistatusSoil

    fun getAllLowLand() {
        viewModelScope.launch {
            repository.getAllLowLand()
                .catch {
                    _uistatus.value = UIStatus.Error(it.message.toString())
                }
                .collect { orderRewards ->
                    _uistatus.value = UIStatus.Success(orderRewards)
                }
        }
    }

    fun getAllSoil() {
        viewModelScope.launch {
            repository.getAllSoil()
                .catch {
                    _uistatusSoil.value = UIStatus.Error(it.message.toString())
                }
                .collect { orderRewards ->
                    _uistatusSoil.value = UIStatus.Success(orderRewards)
                }
        }
    }
}