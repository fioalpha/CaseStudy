package com.fioalpha.poc.form.presentation

import com.fioalpha.poc.domain.FormData

sealed class InvestedInteraction {
    data class ValidatedForm(val data: FormData) : InvestedInteraction()
    object IDLE: InvestedInteraction()
}