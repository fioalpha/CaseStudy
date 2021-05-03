package com.fioalpha.poc.domain

class MaturityDateFieldUseCase: IsFieldFormValidated<String> {
    override fun isValidated(data: String): Boolean {
        return data.count() < 10
    }
}