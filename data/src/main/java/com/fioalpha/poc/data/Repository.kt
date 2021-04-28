package com.fioalpha.poc.data

import com.fioalpha.poc.data.request.SimulatorRequest
import com.fioalpha.poc.data.response.SimulatorResponse
import com.google.gson.Gson

interface Repository {
    suspend fun fetchSimulator(simulatorRequest: SimulatorRequest): SimulatorResponse
}

val responseMock = "{\"investmentParameter\":{\"investedAmount\":32323,\"yearlyInterestRate\":9.5512,\"maturityTotalDays\":1981,\"maturityBusinessDays\":1409,\"maturityDate\":\"2023-03-03T00:00:00\",\"rate\":123,\"isTaxFree\":false},\"grossAmount\":60528.2,\"taxesAmount\":4230.78,\"netAmount\":56297.42,\"grossAmountProfit\":28205.2,\"netAmountProfit\":23974.42,\"annualGrossRateProfit\":87.26,\"monthlyGrossRateProfit\":0.76,\"dailyGrossRateProfit\":0.000445330025305748,\"taxesRate\":15,\"rateProfit\":9.5512,\"annualNetRateProfit\":74.17}"

class SimulatorRepositoryMock: Repository {
    override suspend fun fetchSimulator(simulatorRequest: SimulatorRequest): SimulatorResponse {
        return Gson().fromJson(
            responseMock,
            SimulatorResponse::class.java
        )
    }

}

