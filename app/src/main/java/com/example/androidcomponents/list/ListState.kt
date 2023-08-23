package com.example.androidcomponents.list

import com.example.androidcomponents.placeholder.PlaceholderContent

data class ListState(
    val list: MutableList<PlaceholderContent.PlaceholderItem> = ArrayList(),
)
