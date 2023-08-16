package com.example.androidcomponents.Main

import android.content.Context
import com.example.androidcomponents.Actions.MainAction
import com.example.androidcomponents.ListState
import com.example.androidcomponents.base.BaseViewModel
import com.example.androidcomponents.interfaces.Interactor

class MainViewModel(
    interactors: Set<Interactor<ListState, MainAction>>,
    context: Context
) : BaseViewModel<ListState, MainAction>(interactors, MainReducer()) {

    fun openFragment(notificationState: Boolean) {
        action(MainAction.CheckNotificationState(notificationState))
    }

}