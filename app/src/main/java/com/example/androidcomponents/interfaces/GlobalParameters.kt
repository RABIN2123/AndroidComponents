package com.example.androidcomponents.interfaces

import kotlinx.coroutines.flow.StateFlow

sealed interface GlobalParameters<T> {
    val listState: StateFlow<T>
}