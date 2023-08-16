package com.example.androidcomponents.interfaces

interface Interactor<State, Action> {
    suspend operator fun invoke(state: State, action: Action): Action

    fun canHandle(action: Action): Boolean
}