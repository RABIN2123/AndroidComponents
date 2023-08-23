package com.example.androidcomponents.infoscreen

import androidx.core.content.edit
import com.example.androidcomponents.Actions.InfoAction
import com.example.androidcomponents.interfaces.Interactor
import com.example.androidcomponents.placeholder.PlaceholderContent
import java.lang.IllegalArgumentException

class GetInfoInteractor : Interactor<InfoState, InfoAction> {
    override suspend fun invoke(state: InfoState, action: InfoAction): InfoAction {
        return if (action is InfoAction.LoadInfo) {
            action.prefs.edit {
                putString("id", action.id.toString())
            }
            InfoAction.infoLoaded(PlaceholderContent.ITEMS[action.id-1])
        } else {
            InfoAction.Error(IllegalArgumentException("Wrong action: $action"))
        }
    }

    override fun canHandle(action: InfoAction): Boolean {
        return action is InfoAction.LoadInfo
    }
}