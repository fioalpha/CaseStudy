package com.fioalpha.poc.extensions

const val EMPTY_TEXT = ""
const val ZERO_INT = 0
const val ZERO_DOUBLE = 0.0

fun String?.toDoubleOrZero(): Double {
    return this?.toDoubleOrNull() ?: ZERO_DOUBLE
}

inline fun Boolean.isTrue(action: () -> Unit) {
    if (this) {
        action()
    }
}