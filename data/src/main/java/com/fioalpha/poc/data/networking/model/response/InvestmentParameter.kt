package com.fioalpha.poc.data.networking.model.response

data class InvestmentParameter(
    val investedAmount: Double,
    val isTaxFree: Boolean,
    val maturityBusinessDays: Int,
    val maturityDate: String,
    val maturityTotalDays: Int,
    val rate: Double,
    val yearlyInterestRate: Double
)
