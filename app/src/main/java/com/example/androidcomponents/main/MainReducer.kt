package com.example.androidcomponents.main

import com.example.androidcomponents.Actions.MainAction
import com.example.androidcomponents.interfaces.Reducer

class MainReducer: Reducer<MainState, MainAction> {
    override val initialState = MainState()

    override fun reduce(state: MainState, action: MainAction): MainState {
        return when(action) {
            is MainAction.CheckNotificationState -> state
            is MainAction.SelectFragment -> state.copy(
                stateOfScreen = action.status,
                id = action.id

            )
            is MainAction.Error -> state
        }
    }
}