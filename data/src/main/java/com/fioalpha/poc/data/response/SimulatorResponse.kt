package com.fioalpha.poc.data.response

data class  SimulatorResponse (
    val investmentParameter: InvestmentParameterResponse?,
    val grossAmount: Float?,
    val taxesAmount: Float?,
    val netAmount: Float?,
    val grossAmountProfit: Float?,
    val netAmountProfit: Float?,
    val annualGrossRateProfit: Float?,
    val monthlyGrossRateProfit: Float?,
    val dailyGrossRateProfit: Float?,
    val taxesRate: Int?,
    val rateProfit: Float?,
    val annualNetRateProfit: Float?
)