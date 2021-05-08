package com.fioalpha.poc.domain

class InvestedAmountFieldUseCase: IsFieldFormValidated<Double> {
    override fun isValidated(data: Double): Boolean {
        return data <= 0
    }
}