package com.fioalpha.poc.domain

import org.junit.Assert.*
import org.junit.Test

class MaturityDateFieldUseCaseTest {
    private val maturityDateFieldUseCase = MaturityDateFieldUseCase()

    @Test
    fun `when called isValidated With data length equal 10 characters Then return false`() {
        assertFalse(maturityDateFieldUseCase.isValidated("2022-01-02"))
    }

    @Test
    fun `when called isValidated With data length less than 10 characters Then return false`() {
        assertTrue(maturityDateFieldUseCase.isValidated("2022-0102"))
    }
}
