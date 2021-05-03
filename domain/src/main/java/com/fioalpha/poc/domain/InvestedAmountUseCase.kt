package com.fioalpha.poc.domain

class InvestedAmountFieldUseCase: IsFieldFormValidated<Int> {
    override fun isValidated(data: Int): Boolean {
        return data <= 0
    }
}