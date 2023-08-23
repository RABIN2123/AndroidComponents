package com.example.androidcomponents.main

import android.util.Log
import com.example.androidcomponents.Actions.MainAction
import com.example.androidcomponents.interfaces.Interactor
import java.lang.IllegalArgumentException

class CheckNotificationStateInteractor : Interactor<MainState, MainAction> {
    override suspend fun invoke(state: MainState, action: MainAction): MainAction {
        return if (action is MainAction.CheckNotificationState) {
            val id = action.prefs.getString("id", null) ?: ""
            if (action.notificationState && id.isNotEmpty()) {
                Log.d("TAG", "STATUS_INFO")
                MainAction.SelectFragment(MainState.Status.INFO, id.toInt())
            } else {
                Log.d("TAG", "STATUS_MAIN")
                MainAction.SelectFragment(MainState.Status.MAIN, -1)
            }
        } else {
            MainAction.Error(IllegalArgumentException("Wrong action: $action"))
        }
    }

    override fun canHandle(action: MainAction): Boolean {
        return action is MainAction.CheckNotificationState
    }

}