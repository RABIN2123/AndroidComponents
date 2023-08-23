package com.example.androidcomponents.list

import com.example.androidcomponents.Actions.ListAction
import com.example.androidcomponents.interfaces.Interactor
import com.example.androidcomponents.placeholder.PlaceholderContent

class LoadListInteractor : Interactor<ListState, ListAction> {
    override suspend fun invoke(state: ListState, action: ListAction): ListAction {
        return if (action is ListAction.LoadList) {
            ListAction.ListLoaded(PlaceholderContent.ITEMS)
        } else {
            ListAction.Error(IllegalArgumentException("Wrong action: $action"))
        }
    }

    override fun canHandle(action: ListAction): Boolean {
        return action is ListAction.LoadList
    }
}