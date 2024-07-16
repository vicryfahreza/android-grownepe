package com.vicry.grownepe.ui.state

sealed class UIStatus <out T: Any?>{

    object Loading : UIStatus<Nothing>()

    data class Success<out T: Any>(val data: T) : UIStatus<T>()

    data class Error(val errorMessage: String) : UIStatus<Nothing>()

}