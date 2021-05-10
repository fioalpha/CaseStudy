package com.fioalpha.poc.component.textwatcher

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import com.fioalpha.poc.component.extension.toPrice
import java.text.NumberFormat
import java.util.*

class ValueWatcher(
    private val editText: EditText
) : TextWatcher {
    private var current: CharSequence? = ""
    private val filterTextRegex = Regex("[R$,.]")
    private var isChange: Boolean = false
    private var isAutomatic: Boolean = false

    override fun afterTextChanged(text: Editable?) {
        if (isAutomatic.not()) return
        applyFormat(text.toString())
    }

    override fun beforeTextChanged(charSequence: CharSequence?, start: Int, before: Int, count: Int) {
        isChange = before < count
        isAutomatic = count >= 2
    }

    override fun onTextChanged(text: CharSequence?, p1: Int, p2: Int, p3: Int) {
        if (isCanChangeText(text.toString())) return
        current = text
        applyFormat(text.toString())
    }

    private fun applyFormat(text: String) {
        editText.removeTextChangedListener(this)
        val priceFormat = stringToPriceFormat(text)
        editText.setText(priceFormat)
        editText.setSelection(priceFormat.count())
        editText.addTextChangedListener(this)
    }

    private fun isCanChangeText(text: CharSequence?): Boolean {
        return text.isNullOrEmpty() &&
                current == text &&
                isChange.not()
    }

    private fun stringToPriceFormat(text: String): String {
        val valueClean: Long = try {
            text.replace(filterTextRegex, "")
                .trim()
                .toLong()
        } catch (ex: NumberFormatException) {
            0
        }
        val localeDefault = Locale.getDefault()
        val currency = NumberFormat.getCurrencyInstance(localeDefault).currency
            ?.getSymbol(localeDefault)?.trim() ?: ""
        val number = NumberFormat.getCurrencyInstance(localeDefault)
            .format(valueClean.toPrice())
            .replace(currency, "")
            .trim()

        return "$currency $number"
    }
}
