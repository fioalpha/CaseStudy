package com.fioalpha.poc.component.textwatcher

import android.text.Editable
import android.text.TextWatcher

class DateTextWatcher: TextWatcher {

    private var isCleaning = false

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        isCleaning = count > 0
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
    }

    override fun afterTextChanged(s: Editable?) {
        if (s == null) return
        if (isCleaning.not()) {
            when (s.count()) {
                2 -> s.append("/")
                5 -> s.append("/")
            }
        }
    }

}