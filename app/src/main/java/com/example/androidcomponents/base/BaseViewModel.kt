package com.example.androidcomponents.base

import android.content.Context
import androidx.annotation.MainThread
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidcomponents.ListState
import com.example.androidcomponents.interfaces.Interactor
import com.example.androidcomponents.interfaces.Reducer
import com.example.androidcomponents.util.delegate
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

abstract class BaseViewModel<State, Action>(
    private val interactors: Set<Interactor<State, Action>>,
    private val reducer: Reducer<State, Action>
) : ViewModel() {
    private val _listState = MutableStateFlow(reducer.initialState)
    private var stateValue by _listState.delegate()
    val listState: StateFlow<State> = _listState

    @MainThread
    protected fun action(action: Action) {
        stateValue = reducer.reduce(stateValue, action)
        interactors.filter { it.canHandle(action) }.forEach { interactor ->
            viewModelScope.launch(Dispatchers.IO) {
                val newResultAction = interactor.invoke(stateValue, action)
                withContext(Dispatchers.Main) {
                    action(newResultAction)
                }
            }
        }
    }
}