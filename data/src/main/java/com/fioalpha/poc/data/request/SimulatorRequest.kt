package com.fioalpha.poc.data.request

data class SimulatorRequest(
    val data: String?,
    val investedAmount: Int?,
    val rate: Int?,
    val maturityDate: String?,
    val isTaxFree: Boolean = false,
    val index: String = "CDI"
)