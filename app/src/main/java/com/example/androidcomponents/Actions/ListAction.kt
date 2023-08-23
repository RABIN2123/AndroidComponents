package com.example.androidcomponents.Actions

import com.example.androidcomponents.placeholder.PlaceholderContent
import java.lang.Exception

sealed class ListAction {
    object LoadList: ListAction()
    class ListLoaded(val list: MutableList<PlaceholderContent.PlaceholderItem>): ListAction()

    class Error(val error: Exception): ListAction()
}