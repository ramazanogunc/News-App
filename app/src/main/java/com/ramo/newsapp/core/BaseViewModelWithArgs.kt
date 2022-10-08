package com.ramo.newsapp.core

import androidx.lifecycle.SavedStateHandle

abstract class BaseViewModelWithArgs<ARGS>(
    savedStateHandle: SavedStateHandle,
    getArgs: (SavedStateHandle) -> ARGS
) : BaseViewModel() {

    val args: ARGS = getArgs.invoke(savedStateHandle)

}