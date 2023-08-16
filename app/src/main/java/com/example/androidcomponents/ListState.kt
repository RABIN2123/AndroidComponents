package com.example.androidcomponents

import com.example.androidcomponents.interfaces.SupportInfoViewModel
import com.example.androidcomponents.interfaces.SupportListViewModel
import com.example.androidcomponents.interfaces.SupportMainViewModel
import com.example.androidcomponents.placeholder.PlaceholderContent

data class ListState(
    override val list: MutableList<PlaceholderContent.PlaceholderItem> = ArrayList(),
    override val info: PlaceholderContent.PlaceholderItem = PlaceholderContent.PlaceholderItem(
        "",
        "",
        ""
    ),
    override val stateOfScreen: Status = Status.MAIN,
    val id: Int = 0
) : SupportMainViewModel, SupportListViewModel, SupportInfoViewModel {
    enum class Status {
        MAIN,
        INFO
    }
}
