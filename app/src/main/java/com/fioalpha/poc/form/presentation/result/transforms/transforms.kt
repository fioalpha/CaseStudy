package com.fioalpha.poc.form.presentation.result.transforms

import com.fioalpha.poc.component.view.ResultInvestmentHeadModel
import com.fioalpha.poc.component.view.model.ResultInvestmentBottomModel
import com.fioalpha.poc.component.view.model.ResultInvestmentMediumModel
import com.fioalpha.poc.domain.model.Investment

fun Investment.transformBottom(): ResultInvestmentBottomModel {
    return ResultInvestmentBottomModel(
        dateRecover = this.investmentParameter.maturityDate,
        days = this.investmentParameter.maturityTotalDays,
        investmentMonth = this.monthlyGrossRateProfit,
        cdiPercentage = this.investmentParameter.rate,
        annualGrossRate = this.annualGrossRateProfit,
        profitabilityTime = this.dailyGrossRateProfit
    )
}

fun Investment.transformHead(): ResultInvestmentHeadModel {
    return ResultInvestmentHeadModel(
        investmentAmount = this.grossAmount,
        yieldValue = this.rateProfit
    )
}

fun Investment.transformMedium(): ResultInvestmentMediumModel {
    return ResultInvestmentMediumModel(
        investmentStart = this.investmentParameter.investedAmount,
        grossInvestment = this.grossAmountProfit,
        incomeValue = this.monthlyGrossRateProfit,
        taxesAmount = this.taxesAmount,
        netInvestmentValue = this.netAmountProfit,
        taxesRate = this.taxesRate
    )
}
