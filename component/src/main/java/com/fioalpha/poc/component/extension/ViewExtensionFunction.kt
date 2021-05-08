package com.fioalpha.poc.component.extension

import android.view.View
import android.widget.EditText
import com.fioalpha.poc.extensions.EMPTY_TEXT
import com.fioalpha.poc.extensions.ZERO_INT
import com.fioalpha.poc.extensions.toDoubleOrZero

fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.hide() {
    this.visibility = View.GONE
}

const val DAY_INDEX = 2
const val MOUTH_INDEX = 2
const val YEAR_INDEX = 2
const val SLASH_DELIMITER = "/"
const val DOT_DELIMITER = "."
const val COMMA_DELIMITER = ","
const val TRACE_DELIMITER = "-"

const val LENGTH_DATE = 3

fun EditText.convertDate(): String {
    val textSplit = this.text.split(SLASH_DELIMITER)
    return if (textSplit.count() == LENGTH_DATE) {
        "${textSplit[DAY_INDEX]}$TRACE_DELIMITER${MOUTH_INDEX}$TRACE_DELIMITER${YEAR_INDEX}"
    } else {
        EMPTY_TEXT
    }
}

fun EditText.clearCharacters(regex: Regex): Int {
    return this.text?.replace(regex, EMPTY_TEXT)
        ?.toIntOrNull() ?: ZERO_INT
}

fun EditText.convertToDouble(regex: Regex): Double {
    return this.text?.replace(regex, EMPTY_TEXT)
        ?.replace(COMMA_DELIMITER, DOT_DELIMITER)
        .toDoubleOrZero()
}