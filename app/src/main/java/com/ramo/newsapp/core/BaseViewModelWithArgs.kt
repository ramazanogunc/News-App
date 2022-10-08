package com.ramo.newsapp.core

import androidx.annotation.StringRes
import androidx.lifecycle.*
import androidx.navigation.NavDirections
import com.ramo.newsapp.core.lifecycle.SingleLiveEvent
import com.ramo.newsapp.core.state.DialogEvent
import com.ramo.newsapp.core.state.NavEvent
import kotlinx.coroutines.launch

abstract class BaseViewModelWithArgs<ARGS>(
    savedStateHandle: SavedStateHandle,
    getArgs: (SavedStateHandle) -> ARGS
) : BaseViewModel() {

    val args: ARGS = getArgs.invoke(savedStateHandle)

}