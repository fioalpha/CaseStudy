package com.fioalpha.poc.component.extension

import android.annotation.SuppressLint
import android.widget.TextView
import java.text.NumberFormat
import java.util.*

@SuppressLint("SetTextI18n")
fun TextView.setPercentageValue(value: Double) {
    this.text = "${value.toString().replace(".", ",")}%"
}

fun TextView.setMonetary(value: Double) {
    val valueFormat = NumberFormat.getCurrencyInstance(Locale.getDefault())
        .format(value)
    text = valueFormat
}

@SuppressLint("SetTextI18n")
fun TextView.setMoneyWithTaxes(value: Double, taxesRate: Double) {
    val valueFormat = NumberFormat.getCurrencyInstance(Locale.getDefault())
        .format(value)
    text = "$valueFormat[$taxesRate%]"
}


