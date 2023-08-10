package com.example.androidcomponents.interfaces

import com.example.androidcomponents.placeholder.PlaceholderContent
import kotlinx.coroutines.flow.StateFlow

interface ListViewModel {
    val listState: StateFlow<SupportListViewModel>
    fun setId(id: Int)
}

interface SupportListViewModel {
    val list: MutableList<PlaceholderContent.PlaceholderItem>
}