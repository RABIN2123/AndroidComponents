package com.example.androidcomponents

import android.content.Context
import androidx.core.content.edit
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.androidcomponents.interfaces.InfoViewModel
import com.example.androidcomponents.interfaces.ListViewModel
import com.example.androidcomponents.interfaces.MainViewModel
import com.example.androidcomponents.interfaces.SupportInfoViewModel
import com.example.androidcomponents.interfaces.SupportListViewModel
import com.example.androidcomponents.interfaces.SupportMainViewModel
import com.example.androidcomponents.placeholder.PlaceholderContent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class SharedViewModel(
    private val infoRepository: PlaceholderContent,
    context: Context
) :
    ViewModel(), MainViewModel, ListViewModel, InfoViewModel {
    private val _listState = MutableStateFlow(ListState())
    private val prefs = context.getSharedPreferences("savedId", Context.MODE_PRIVATE)
    override val listState: StateFlow<ListState> = _listState


    override fun selectFragment(notificationState: Boolean) {
        val savedId = prefs.getString("id", null) ?: ""
        _listState.update {
            if (notificationState && savedId.isNotEmpty()) {
                it.copy(
                    stateOfScreen = ListState.Status.INFO,
                    id = savedId.toInt()
                )
            } else {
                it.copy(stateOfScreen = ListState.Status.MAIN)
            }
        }
    }

    override fun setId(id: Int) {
        _listState.update {
            it.copy(
                stateOfScreen = ListState.Status.INFO,
                id = id
            )
        }
    }

    override fun getInfo() {
        val id = _listState.value.id
        _listState.update {
            it.copy(
                info = _listState.value.list[id - 1]
            )
        }
        prefs.edit {
            putString("id", id.toString())
        }
    }


    init {
        initData()
    }

    private fun initData() {
        viewModelScope.launch(Dispatchers.IO) {
            _listState.update {
                it.copy(
                    list = infoRepository.ITEMS
                )
            }
        }
    }

    companion object {
        fun provideFactory(
            infoRepository: PlaceholderContent,
            context: Context,
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return SharedViewModel(infoRepository, context) as T

            }
        }
    }
}