package com.fioalpha.poc.component.view.model

data class ResultInvestmentBottomModel(
    val dateRecover: String,
    val days: Int,
    val investmentMonth: Double,
    val cdiPercentage: Double,
    val annualGrossRate: Double,
    val profitabilityTime: Double,
)