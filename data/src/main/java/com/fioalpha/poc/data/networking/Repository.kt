package com.fioalpha.poc.data.networking

import com.fioalpha.poc.data.networking.model.RetrofitService
import com.fioalpha.poc.data.networking.model.response.CalculateInvestmentResponse
import com.fioalpha.poc.domain.model.FormData
import com.fioalpha.poc.domain.model.Investment
import com.fioalpha.poc.domain.model.InvestmentParameter
import com.fioalpha.poc.domain.repository.Repository

class RepositoryImpl(
    private val retrofitService: RetrofitService
): Repository {
    override suspend fun fetchInvestment(formData: FormData): Investment {
        return retrofitService.fetchInvestment(
            formData.investedAmount,
            formData.maturityDate,
            formData.rate
        ).run {
            transformDomain()
        }
    }

}

fun CalculateInvestmentResponse.transformDomain(): Investment {
    return Investment(
        annualGrossRateProfit = annualGrossRateProfit,
        annualNetRateProfit = annualNetRateProfit,
        dailyGrossRateProfit = dailyGrossRateProfit,
        grossAmount = grossAmount,
        grossAmountProfit = grossAmountProfit,
        investmentParameter = InvestmentParameter(
            investedAmount =  investmentParameter.investedAmount,
            isTaxFree = investmentParameter.isTaxFree,
            maturityBusinessDays = investmentParameter.maturityBusinessDays,
            maturityDate = investmentParameter.maturityDate,
            maturityTotalDays = investmentParameter.maturityTotalDays,
            rate = investmentParameter.rate,
            yearlyInterestRate = investmentParameter.yearlyInterestRate
        ),
        monthlyGrossRateProfit = monthlyGrossRateProfit,
        netAmount = netAmount,
        netAmountProfit = netAmountProfit,
        rateProfit = rateProfit,
        taxesAmount = taxesAmount,
        taxesRate = taxesRate
    )
}
