package com.vicry.grownepe.ui.screen.home.homenepe

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vicry.grownepe.model.home.StateLowLandNepe
import com.vicry.grownepe.model.repo.NepenthesRepository
import com.vicry.grownepe.ui.state.UIStatus
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeNepeViewModel (
    private val repo: NepenthesRepository
) : ViewModel() {
    private val _uiStatus: MutableStateFlow<UIStatus<StateLowLandNepe>> =
        MutableStateFlow(UIStatus.Loading)
    val uiStatus: StateFlow<UIStatus<StateLowLandNepe>>
        get() = _uiStatus

    fun getHomeNepeById(homenepeId: Long) {
        viewModelScope.launch {
            repo.getHomeNepeById(homenepeId)
                .catch {
                    _uiStatus.value = UIStatus.Error(it.message.toString())
                }
                .collect { homeNepe ->
                    _uiStatus.value = UIStatus.Success(homeNepe)
                }
        }
    }

}