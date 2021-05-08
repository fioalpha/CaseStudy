package com.fioalpha.poc.domain.model

import java.io.Serializable

data class FormData(
    val investedAmount: Double,
    val rate: Int,
    val maturityDate: String
): Serializable
