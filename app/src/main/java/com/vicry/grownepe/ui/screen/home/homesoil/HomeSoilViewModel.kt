package com.vicry.grownepe.ui.screen.home.homesoil

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vicry.grownepe.model.home.StateSoil
import com.vicry.grownepe.model.repo.NepenthesRepository
import com.vicry.grownepe.ui.state.UIStatus
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeSoilViewModel (
    private val repo: NepenthesRepository
) : ViewModel() {
    private val _uiStatus: MutableStateFlow<UIStatus<StateSoil>> =
        MutableStateFlow(UIStatus.Loading)
    val uiStatus: StateFlow<UIStatus<StateSoil>>
        get() = _uiStatus

    fun getHomeSoilById(nepenthesId: Long) {
        viewModelScope.launch {
            repo.getHomeSoilById(nepenthesId)
                .catch {
                    _uiStatus.value = UIStatus.Error(it.message.toString())
                }
                .collect { homeSoil ->
                    _uiStatus.value = UIStatus.Success(homeSoil)
                }
        }
    }



}