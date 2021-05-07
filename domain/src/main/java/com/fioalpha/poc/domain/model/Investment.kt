package com.fioalpha.poc.domain.model

data class Investment(
    val annualGrossRateProfit: Double,
    val annualNetRateProfit: Double,
    val dailyGrossRateProfit: Double,
    val grossAmount: Double,
    val grossAmountProfit: Double,
    val investmentParameter: InvestmentParameter,
    val monthlyGrossRateProfit: Double,
    val netAmount: Double,
    val netAmountProfit: Double,
    val rateProfit: Double,
    val taxesAmount: Double,
    val taxesRate: Double
)
