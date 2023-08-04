package com.example.androidcomponents.presenters.interfaces

interface MainActivityView {

    fun getState(): Boolean
    fun startInfoElementFragment(savedId: Int)
    fun startElementListFragment()

}