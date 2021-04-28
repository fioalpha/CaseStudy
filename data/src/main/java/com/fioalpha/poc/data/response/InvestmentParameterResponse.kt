package com.fioalpha.poc.data.response

data class InvestmentParameterResponse(
    val investedAmount: Long?,
    val yearlyInterestRate: Double?,
    val maturityTotalDays: Int?,
    val maturityBusinessDays: Int?,
    val maturityDate: String?,
    val rate: Long?,
    val isTaxFree: Boolean?
)