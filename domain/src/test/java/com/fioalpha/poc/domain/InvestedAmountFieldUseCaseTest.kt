package com.fioalpha.poc.domain

import org.junit.Assert.*
import org.junit.Test

class InvestedAmountFieldUseCaseTest {
    private val investedAmountFieldUseCase = InvestedAmountFieldUseCase()

    @Test
    fun `when called isValidated invested With value bigger than Zero Then return false` () {
        assertFalse(investedAmountFieldUseCase.isValidated(20))
    }

    @Test
    fun `when called isValidated invested With value equal than Zero Then return false` () {
        assertTrue(investedAmountFieldUseCase.isValidated(0))
    }

}