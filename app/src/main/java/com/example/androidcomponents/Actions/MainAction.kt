package com.example.androidcomponents.Actions

import com.example.androidcomponents.ListState
import java.lang.Exception

sealed class MainAction {
    class CheckNotificationState(val notificationState: Boolean) : MainAction()

    class SelectFragment(val status: ListState.Status) : MainAction()

    class Error(val error: Exception): MainAction()
}