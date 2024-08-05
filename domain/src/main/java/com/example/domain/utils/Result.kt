package com.example.domain.utils

sealed class Result<T> {
    class Success<T>(val data: T) : Result<T>()
    class Error<T>(val error: String?) : Result<T>()
}