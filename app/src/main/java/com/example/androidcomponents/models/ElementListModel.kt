package com.example.androidcomponents.models

import com.example.androidcomponents.placeholder.PlaceholderContent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ElementListModel {

    fun loadData(callback: LoadDataCallback<List<PlaceholderContent.PlaceholderItem>>) {
        CoroutineScope(Dispatchers.IO).launch {
            callback.onLoad(PlaceholderContent.ITEMS)
        }
    }

}

interface LoadDataCallback<T> {
    fun onLoad(values: T)

}