package com.example.androidcomponents.models

import android.content.Context
import com.example.androidcomponents.placeholder.PlaceholderContent
import androidx.core.content.edit

class InfoElementModel(context: Context?) {
    private val prefs = context?.getSharedPreferences("savedId", Context.MODE_PRIVATE)
    fun loadData(callback: LoadInfoCallback) {
        val id = callback.getId()
        prefs?.edit {
            putString("id", id.toString())
        }
        callback.onLoad(PlaceholderContent.ITEMS[id!! - 1])
    }
}

interface LoadInfoCallback : LoadDataCallback<PlaceholderContent.PlaceholderItem> {
    fun getId(): Int?
}