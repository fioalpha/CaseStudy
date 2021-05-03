package com.fioalpha.poc.domain

class RateFieldUseCase: IsFieldFormValidated<Int> {
    override fun isValidated(data: Int): Boolean {
        return data == 0
    }
}