package com.fioalpha.poc.component.view

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.LinearLayout
import com.fioalpha.poc.component.databinding.InvestmentResultMediumBinding
import com.fioalpha.poc.component.extension.setMonetary
import com.fioalpha.poc.component.extension.setMoneyWithTaxes
import com.fioalpha.poc.component.view.model.ResultInvestmentMediumModel
import java.text.NumberFormat
import java.util.*


class ResultInvestmentMedium @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0
): LinearLayout(context, attributeSet, defStyleAttr) {

    private val viewBinding:InvestmentResultMediumBinding by lazy {
        InvestmentResultMediumBinding.inflate(LayoutInflater.from(context))
    }

    init {
       addView(
           viewBinding.root, LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT)
       )
    }

    fun bind(data: ResultInvestmentMediumModel) {
        viewBinding.tvInvestedStarting.setMonetary(data.investmentStart)
        viewBinding.tvGrossInvestment.setMonetary(data.grossInvestment)
        viewBinding.tvIncomeValue.setMonetary(data.incomeValue)
        viewBinding.tvTaxes.setMoneyWithTaxes(data.taxesAmount, data.taxesRate)
        viewBinding.tvNetInvestmentValue.setMonetary(data.netInvestmentValue)
    }

}
