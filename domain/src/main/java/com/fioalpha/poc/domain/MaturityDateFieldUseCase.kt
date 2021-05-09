package com.fioalpha.poc.domain

private const val NUMBER_LENGTH_MAX = 10

class MaturityDateFieldUseCase : IsFieldFormValidated<String> {
    override fun isValidated(data: String): Boolean {
        return data.count() < NUMBER_LENGTH_MAX
    }
}
