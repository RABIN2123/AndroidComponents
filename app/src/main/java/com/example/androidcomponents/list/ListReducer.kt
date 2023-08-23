package com.example.androidcomponents.list

import com.example.androidcomponents.Actions.ListAction
import com.example.androidcomponents.interfaces.Reducer

class ListReducer : Reducer<ListState, ListAction> {
    override val initialState: ListState = ListState()

    override fun reduce(state: ListState, action: ListAction): ListState {
        return when (action) {
            ListAction.LoadList -> state
            is ListAction.ListLoaded -> {
                state.copy(
                    list = action.list
                )
            }

            is ListAction.Error -> state
        }
    }
}