package com.example.androidcomponents.interfaces

import com.example.androidcomponents.placeholder.PlaceholderContent

interface InfoViewModel : GlobalParameters<SupportInfoViewModel> {
    fun getInfo()
}

interface SupportInfoViewModel {
    val info: PlaceholderContent.PlaceholderItem
}