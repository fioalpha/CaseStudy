package com.fioalpha.poc.domain

import com.fioalpha.poc.extensions.ZERO_INT

class InvestedAmountFieldUseCase: IsFieldFormValidated<Double> {
    override fun isValidated(data: Double): Boolean {
        return data <= ZERO_INT
    }
}
