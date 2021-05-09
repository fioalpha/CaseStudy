package com.fioalpha.poc.component.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.fioalpha.poc.component.databinding.InvestmentResultBottomBinding
import com.fioalpha.poc.component.extension.setPercentageValue
import com.fioalpha.poc.component.view.model.ResultInvestmentBottomModel

class ResultInvestmentBottom @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attributeSet, defStyleAttr) {

    private val viewBinding: InvestmentResultBottomBinding by lazy {
        InvestmentResultBottomBinding.inflate(LayoutInflater.from(context))
    }

    init {
       addView(viewBinding.root, LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT))
    }

    fun bind(data: ResultInvestmentBottomModel) {
        viewBinding.tvDateRecover.text = data.dateRecover
        viewBinding.tvDayAccumulate.text = data.days.toString()
        viewBinding.tvInvestmentMonth.setPercentageValue(data.investmentMonth)
        viewBinding.tvCdiPercentage.setPercentageValue(data.cdiPercentage)
        viewBinding.tvAnnualGrossRateProfit.setPercentageValue(data.annualGrossRate)
        viewBinding.tvProfitabilityTime.setPercentageValue(data.profitabilityTime)
    }
}
