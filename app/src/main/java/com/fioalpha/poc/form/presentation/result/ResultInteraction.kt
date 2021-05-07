package com.fioalpha.poc.form.presentation.result

import com.fioalpha.poc.domain.FormData

sealed class ResultInteraction {
    data class Calculate(val form: FormData): ResultInteraction()
}