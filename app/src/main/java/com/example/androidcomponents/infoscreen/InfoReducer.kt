package com.example.androidcomponents.infoscreen

import com.example.androidcomponents.Actions.InfoAction
import com.example.androidcomponents.interfaces.Reducer
import com.example.androidcomponents.placeholder.PlaceholderContent

class InfoReducer : Reducer<InfoState, InfoAction> {
    override val initialState: InfoState = InfoState(
        PlaceholderContent.PlaceholderItem("", "", "")
    )

    override fun reduce(state: InfoState, action: InfoAction): InfoState {
        return when (action) {
            is InfoAction.LoadInfo -> state
            is InfoAction.infoLoaded -> state.copy(
                info = action.info
            )

            is InfoAction.Error -> state
        }
    }
}
