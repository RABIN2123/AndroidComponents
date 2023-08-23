package com.example.androidcomponents.Actions

import android.content.SharedPreferences
import com.example.androidcomponents.placeholder.PlaceholderContent
import java.lang.Exception

sealed class InfoAction {
    class LoadInfo(val id: Int, val prefs: SharedPreferences) : InfoAction()
    class infoLoaded(val info: PlaceholderContent.PlaceholderItem) : InfoAction()
    class Error(val error: Exception) : InfoAction()
}