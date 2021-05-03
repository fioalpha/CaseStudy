package com.fioalpha.poc.domain

import com.fioalpha.poc.domain.model.Investment

interface CalculateInvestedUseCase {
    suspend fun execute(formData: FormData): Investment
}

