package com.example.androidcomponents.presenters

import com.example.androidcomponents.models.ElementListModel
import com.example.androidcomponents.models.LoadDataCallback
import com.example.androidcomponents.placeholder.PlaceholderContent
import com.example.androidcomponents.presenters.interfaces.ElementListView

class ElementListPresenter(private val model: ElementListModel) {

    private var view: ElementListView<List<PlaceholderContent.PlaceholderItem>>? = null

    fun attachView(view: ElementListView<List<PlaceholderContent.PlaceholderItem>>) {
        this.view = view
    }

    fun viewIsReady() {
        loadData()
    }

    private fun loadData() {
        model.loadData(object : LoadDataCallback<List<PlaceholderContent.PlaceholderItem>> {
            override fun onLoad(values: List<PlaceholderContent.PlaceholderItem>) {
                view?.showInfo(values)
            }
        })
    }

    fun detachView() {
        view = null
    }
}