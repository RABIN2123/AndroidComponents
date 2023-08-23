package com.example.androidcomponents.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.androidcomponents.Actions.ListAction
import com.example.androidcomponents.base.BaseViewModel
import com.example.androidcomponents.interfaces.Interactor



class ListViewModel(
    interactors: Set<Interactor<ListState, ListAction>>
) : BaseViewModel<ListState, ListAction>(interactors, ListReducer()) {

    fun loadData() {
        action(ListAction.LoadList)
    }
    companion object {
        fun provideFactory(
            interactors: Set<Interactor<ListState, ListAction>>,
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return ListViewModel(interactors) as T
            }
        }
    }
}