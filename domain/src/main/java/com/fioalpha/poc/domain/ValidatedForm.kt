package com.fioalpha.poc.domain

import java.io.Serializable

interface ValidatedForm {
    fun isValidated(form: FormData): List<String>
}

const val INVESTED_AMOUNT_FIELD = "INVESTED_AMOUNT_FIELD"
const val RATE_FIELD = "RATE_FIELD"
const val MATURITY_DATE_FIELD = "MATURITY_DATE_FIELD"

class ValidatedFormImpl(
    private val investedAmountFieldUseCase: IsFieldFormValidated<Double>,
    private val rateFieldUseCase: IsFieldFormValidated<Int>,
    private val maturityDateFieldUseCase: IsFieldFormValidated<String>
): ValidatedForm {

    private val errors: MutableList<String> = mutableListOf()

    override fun isValidated(form: FormData): List<String> {
        errors.clear()

        investedAmountFieldUseCase.isValidated(form.investedAmount).isTrue {
            errors.add(INVESTED_AMOUNT_FIELD)
        }

        rateFieldUseCase.isValidated(form.rate).isTrue {
            errors.add(RATE_FIELD)
        }

        maturityDateFieldUseCase.isValidated(form.maturityDate).isTrue {
            errors.add(MATURITY_DATE_FIELD)
        }

        return errors
    }

}

inline fun Boolean.isTrue(action: () -> Unit) {
    if (this) {
        action()
    }
}

data class FormData(
    val investedAmount: Double,
    val rate: Int,
    val maturityDate: String
): Serializable