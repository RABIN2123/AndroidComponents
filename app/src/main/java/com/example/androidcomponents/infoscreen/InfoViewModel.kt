package com.example.androidcomponents.infoscreen

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.androidcomponents.Actions.InfoAction
import com.example.androidcomponents.base.BaseViewModel
import com.example.androidcomponents.interfaces.Interactor

class InfoViewModel(interactors: Set<Interactor<InfoState, InfoAction>>, context: Context) :
    BaseViewModel<InfoState, InfoAction>(interactors, InfoReducer()) {
    private val prefs = context.getSharedPreferences("savedId", Context.MODE_PRIVATE)
    fun loadInfo(id: Int) {
        action(InfoAction.LoadInfo(id, prefs))
    }

    companion object {
        fun provideFactory(
            interactors: Set<Interactor<InfoState, InfoAction>>,
            context: Context
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return InfoViewModel(interactors, context) as T
            }
        }
    }
}