package com.aleexalvz.cashwise.data.model

sealed class AsyncState<out T> {
    object Loading : AsyncState<Nothing>()
    class Success<out T>(val data: T) : AsyncState<T>()
    data class Error(val errorMessage: String) : AsyncState<Nothing>()
}