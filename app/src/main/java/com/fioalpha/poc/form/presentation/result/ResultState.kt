package com.fioalpha.poc.form.presentation.result

import com.fioalpha.poc.component.view.ResultInvestmentHeadModel
import com.fioalpha.poc.component.view.model.ResultInvestmentBottomModel
import com.fioalpha.poc.component.view.model.ResultInvestmentMediumModel

sealed class ResultState {
    object Idle : ResultState()
    object Loader : ResultState()
    data class Result(
        val head: ResultInvestmentHeadModel,
        val medium: ResultInvestmentMediumModel,
        val bottom: ResultInvestmentBottomModel
    ) : ResultState()
    data class Error(val error: String) : ResultState()
}