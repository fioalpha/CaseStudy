package com.fioalpha.poc.component.textwatcher

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

class PercentageTextWatcher(
    private val editText: EditText
): TextWatcher {
    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        editText.removeTextChangedListener(this)
        val textFilter = s.toString().replace("%", "")
        editText.setText("$textFilter%")
        editText.setSelection(lastPosition(textFilter))
        editText.addTextChangedListener(this)
    }

    override fun afterTextChanged(s: Editable?) {

    }

    private fun lastPosition(text: String): Int {
        return if(text.isEmpty()) 0
        else text.length
    }

}
