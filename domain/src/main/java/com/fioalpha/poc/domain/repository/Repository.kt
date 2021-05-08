package com.fioalpha.poc.domain.repository

import com.fioalpha.poc.domain.model.FormData
import com.fioalpha.poc.domain.model.Investment

interface Repository {
    suspend fun fetchInvestment(formData: FormData): Investment
}
