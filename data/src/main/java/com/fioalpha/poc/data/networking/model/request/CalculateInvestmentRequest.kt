package com.fioalpha.poc.data.networking.model.request

data class CalculateInvestmentRequest(
    val index: String,
    val investedAmount: Int,
    val isTaxFree: Boolean,
    val maturityDate: String,
    val rate: Int
)
