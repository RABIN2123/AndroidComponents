package com.example.androidcomponents

import com.example.androidcomponents.Actions.MainAction
import com.example.androidcomponents.interfaces.Interactor
import com.example.androidcomponents.placeholder.PlaceholderContent
import java.lang.IllegalArgumentException

class SelectFragmentInteractor : Interactor<ListState, MainAction> {
    override suspend fun invoke(state: ListState, action: MainAction): MainAction {
        return if (action is MainAction.CheckNotificationState) {
            if (action.notificationState) {
                MainAction.SelectFragment(ListState.Status.INFO)
            } else {
                MainAction.SelectFragment(ListState.Status.MAIN)
            }
        } else {
            MainAction.Error(IllegalArgumentException("Wrong action: $action"))
        }
    }

    override fun canHandle(action: MainAction): Boolean {
        return action is MainAction.CheckNotificationState
    }

}