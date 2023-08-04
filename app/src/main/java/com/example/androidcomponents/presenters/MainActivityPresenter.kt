package com.example.androidcomponents.presenters

import com.example.androidcomponents.models.LoadFragmentCallback
import com.example.androidcomponents.models.MainActivityModel
import com.example.androidcomponents.presenters.interfaces.MainActivityView

class MainActivityPresenter(private val model: MainActivityModel) {

    private var view: MainActivityView? = null
    fun attachView(view: MainActivityView) {
        this.view = view
    }

    fun detachView() {
        view = null
    }

    fun viewIsReady() {
        loadFragment()
    }

    private fun loadFragment() {
        model.loadFragment(object : LoadFragmentCallback {
            override fun onLoadInfoElementFragment(id: Int) {
                view?.startInfoElementFragment(id)
            }

            override fun onLoadElementListFragment() {
                view?.startElementListFragment()
            }

            override fun getState(): Boolean? {
                return view?.getState()
            }
        })
    }

}