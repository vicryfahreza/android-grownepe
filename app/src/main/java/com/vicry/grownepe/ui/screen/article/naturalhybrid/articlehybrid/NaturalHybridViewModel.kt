package com.vicry.grownepe.ui.screen.article.naturalhybrid.articlehybrid

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vicry.grownepe.model.nepenthes.StateNaturalHybrid
import com.vicry.grownepe.model.repo.NepenthesRepository
import com.vicry.grownepe.ui.state.UIStatus
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class NaturalHybridViewModel (
    private val repository: NepenthesRepository
) : ViewModel() {
    private val _uistatus: MutableStateFlow<UIStatus<List<StateNaturalHybrid>>> = MutableStateFlow(
        UIStatus.Loading)
    val uistatus: StateFlow<UIStatus<List<StateNaturalHybrid>>>
        get() = _uistatus

    private val _query = mutableStateOf("")
    val query: State<String> get() = _query

    fun search(newQuery: String) {
        _query.value = newQuery
        _uistatus.value = UIStatus.Success(repository.searchNaturalHybrid(_query.value))
    }

    fun getAllNepenthes() {
        viewModelScope.launch {
            repository.getAllNaturalHybrid()
                .catch {
                    _uistatus.value = UIStatus.Error(it.message.toString())
                }
                .collect { orderRewards ->
                    _uistatus.value = UIStatus.Success(orderRewards)
                }
        }
    }
}