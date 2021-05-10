package com.fioalpha.poc.component.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.fioalpha.poc.component.databinding.InvestResultHeadBinding
import com.fioalpha.poc.component.extension.setMonetary

class ResultInvestmentHead @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attributeSet, defStyleAttr) {

    private val viewBinding: InvestResultHeadBinding by lazy {
        InvestResultHeadBinding.inflate(LayoutInflater.from(context))
    }

    init {
        addView(viewBinding.root)
    }

    fun bind(data: ResultInvestmentHeadModel) {
        viewBinding.investResultAmount.setMonetary(data.yieldValue)
        viewBinding.investResultAmount.text = "Rendimento total de ${data.yieldValue}"
    }
}

data class ResultInvestmentHeadModel(
    val investmentAmount: Double,
    val yieldValue: Double
)
