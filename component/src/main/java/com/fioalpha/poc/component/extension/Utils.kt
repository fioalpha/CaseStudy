package com.fioalpha.poc.component.extension

const val ONE_HUNDRED = 100.0

fun Long.toPrice() = this / ONE_HUNDRED
