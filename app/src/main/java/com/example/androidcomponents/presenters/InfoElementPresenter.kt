package com.example.androidcomponents.presenters

import com.example.androidcomponents.InfoElementFragment
import com.example.androidcomponents.models.InfoElementModel
import com.example.androidcomponents.models.LoadInfoCallback
import com.example.androidcomponents.placeholder.PlaceholderContent
import com.example.androidcomponents.presenters.interfaces.InfoElementView

class InfoElementPresenter(private val model: InfoElementModel) {


    private var view: InfoElementView? = null
    fun attachView(view: InfoElementFragment) {
        this.view = view
    }

    fun viewIsReady() {
        loadData()
    }

    private fun loadData() {
        model.loadData(object : LoadInfoCallback {
            override fun getId(): Int? {
                return view?.getIdInfo()
            }

            override fun onLoad(values: PlaceholderContent.PlaceholderItem) {
                view?.showInfo(values)
            }
        })
    }

    fun detachView() {
        view = null
    }
}