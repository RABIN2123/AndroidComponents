package com.example.androidcomponents.main

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.androidcomponents.Actions.MainAction
import com.example.androidcomponents.base.BaseViewModel
import com.example.androidcomponents.interfaces.Interactor

class MainViewModel(
    interactors: Set<Interactor<MainState, MainAction>>,
    context: Context
) : BaseViewModel<MainState, MainAction>(interactors, MainReducer()) {
    private val prefs = context.getSharedPreferences("savedId", Context.MODE_PRIVATE)
    fun openFragment(notificationState: Boolean) {
        action(MainAction.CheckNotificationState(notificationState, prefs))
    }


    companion object {
        fun provideFactory(
            interactors: Set<Interactor<MainState, MainAction>>,
            applicationContext: Context
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return MainViewModel(interactors, applicationContext) as T
            }
        }
    }
}