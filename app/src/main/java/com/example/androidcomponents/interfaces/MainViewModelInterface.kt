package com.example.androidcomponents.interfaces

import com.example.androidcomponents.ListState
import kotlinx.coroutines.flow.StateFlow

interface MainViewModel {
    val listState: StateFlow<SupportMainViewModel>

    fun selectFragment(notificationState: Boolean)
}

interface SupportMainViewModel {
    val stateOfScreen: ListState.Status
}