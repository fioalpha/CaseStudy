package com.fioalpha.poc.data.networking.model

import com.fioalpha.poc.data.networking.model.response.CalculateInvestmentResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {
    @GET("/v3/ecfaebf5-782b-4b24-ae4f-23b5c3a861da")
    suspend fun fetchInvestment(
        @Query("investedAmount") investedAmount: Double,
        @Query("maturityDate") maturityDate: String,
        @Query("rate") rate: Int,
        @Query("isTaxFree") isTaxFree: Boolean = false,
        @Query("index") index: String = "CDI",
    ): CalculateInvestmentResponse
}
