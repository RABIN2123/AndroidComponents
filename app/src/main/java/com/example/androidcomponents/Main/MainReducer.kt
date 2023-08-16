package com.example.androidcomponents.Main

import com.example.androidcomponents.Actions.MainAction
import com.example.androidcomponents.ListState
import com.example.androidcomponents.interfaces.Reducer

class MainReducer: Reducer<ListState, MainAction> {
    override val initialState = ListState()

    override fun reduce(state: ListState, action: MainAction): ListState {
        return when(action) {
            is MainAction.CheckNotificationState -> state
            is MainAction.SelectFragment -> state.copy(
                stateOfScreen = action.status
            )
            is MainAction.Error -> state
        }
    }
}