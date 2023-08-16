package com.example.androidcomponents.interfaces

interface Reducer<State, Action> {
    val initialState: State
    fun reduce(state: State, action: Action): State
}