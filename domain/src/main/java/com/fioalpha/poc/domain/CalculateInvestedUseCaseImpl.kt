package com.fioalpha.poc.domain

import com.fioalpha.poc.domain.model.FormData
import com.fioalpha.poc.domain.model.Investment
import com.fioalpha.poc.domain.repository.Repository

class CalculateInvestedUseCaseImpl(
    private val repository: Repository
): CalculateInvestedUseCase {
    override suspend fun execute(formData: FormData): Investment {
        return repository.fetchInvestment(formData)
    }
}
