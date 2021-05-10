package com.fioalpha.poc.domain

import org.junit.Assert.*
import org.junit.Test

class RateFieldUseCaseTest {
    private val rateFieldUseCase = RateFieldUseCase()

    @Test
    fun `when called isValidated With data bigger than zero Then return false`() {
        assertFalse(rateFieldUseCase.isValidated(10))
    }

    @Test
    fun `when called isValidated With data equal to zero Then return true`() {
        assertTrue(rateFieldUseCase.isValidated(0))
    }
}
