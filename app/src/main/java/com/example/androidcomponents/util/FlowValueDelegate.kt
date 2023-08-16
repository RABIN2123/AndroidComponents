package com.example.androidcomponents.util

import com.example.androidcomponents.base.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

fun <T> MutableStateFlow<T>.delegate() = FlowValueDelegate(this)

class FlowValueDelegate<T>(private val flowData: MutableStateFlow<T>) :
    ReadWriteProperty<BaseViewModel<*, *>, T> {
    override fun getValue(thisRef: BaseViewModel<*, *>, property: KProperty<*>): T {
        return flowData.value!!
    }

    override fun setValue(thisRef: BaseViewModel<*, *>, property: KProperty<*>, value: T) {
        flowData.value = value
    }
}