package com.example.androidcomponents.Actions

import android.content.SharedPreferences
import com.example.androidcomponents.main.MainState
import java.lang.Exception

sealed class MainAction {
    class CheckNotificationState(val notificationState: Boolean,val prefs: SharedPreferences) : MainAction()

    class SelectFragment(val status: MainState.Status, val id: Int) : MainAction()

    class Error(val error: Exception): MainAction()
}