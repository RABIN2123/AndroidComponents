package com.example.androidcomponents.main

data class MainState(
    val stateOfScreen: Status = Status.MAIN,
    val id: Int = 0
) {
    enum class Status {
        MAIN,
        INFO
    }
}