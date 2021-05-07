package com.fioalpha.poc.component.view.model

data class ResultInvestmentMediumModel(
    val investmentStart: Double,
    val grossInvestment: Double,
    val incomeValue: Double,
    val taxesAmount: Double,
    val netInvestmentValue: Double,
    val taxesRate: Double
)