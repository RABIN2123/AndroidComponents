package com.example.androidcomponents.models

import android.content.Context

class MainActivityModel(context: Context) {
    private val prefs = context.getSharedPreferences("savedId", Context.MODE_PRIVATE)

    fun loadFragment(callback: LoadFragmentCallback) {
        val savedId = prefs.getString("id", null) ?: ""
        val state = callback.getState() ?: false
        if (state && savedId.isNotEmpty()) {
            callback.onLoadInfoElementFragment(savedId.toInt())
        } else {
            callback.onLoadElementListFragment()
        }
    }

}

interface LoadFragmentCallback {
    fun onLoadInfoElementFragment(id: Int)
    fun onLoadElementListFragment()
    fun getState(): Boolean?
}