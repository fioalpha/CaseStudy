package com.fioalpha.poc.domain

import com.fioalpha.poc.extensions.ZERO_INT

class RateFieldUseCase: IsFieldFormValidated<Int> {
    override fun isValidated(data: Int): Boolean {
        return data == ZERO_INT
    }
}
