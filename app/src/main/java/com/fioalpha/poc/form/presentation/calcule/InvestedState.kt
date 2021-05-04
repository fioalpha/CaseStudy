package com.fioalpha.poc.form.presentation

import com.fioalpha.poc.domain.FormData

sealed class InvestedState {
    object Idle: InvestedState()
    data class Success(val data: FormData): InvestedState()
    data class ErrorForms(val errors: List<String>): InvestedState()
}