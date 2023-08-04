package com.example.androidcomponents.presenters.interfaces

import com.example.androidcomponents.placeholder.PlaceholderContent

interface InfoElementView : ElementListView<PlaceholderContent.PlaceholderItem> {
    fun getIdInfo(): Int?
}