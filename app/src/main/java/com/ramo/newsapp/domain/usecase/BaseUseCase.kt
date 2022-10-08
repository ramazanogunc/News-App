package com.ramo.newsapp.domain.usecase

abstract class BaseUseCase<in PARAMS, out RESPONSE> {

    abstract suspend operator fun invoke(params: PARAMS): RESPONSE

}

