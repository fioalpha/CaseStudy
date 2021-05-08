package com.fioalpha.poc.form.presentation.calcule

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.viewbinding.ViewBinding
import com.fioalpha.poc.component.extension.clearCharacters
import com.fioalpha.poc.component.extension.convertDate
import com.fioalpha.poc.component.extension.convertToDouble
import com.fioalpha.poc.component.textwatcher.DateTextWatcher
import com.fioalpha.poc.component.textwatcher.PercentageTextWatcher
import com.fioalpha.poc.component.textwatcher.ValueWatcher
import com.fioalpha.poc.domain.model.FormData
import com.fioalpha.poc.domain.INVESTED_AMOUNT_FIELD
import com.fioalpha.poc.domain.MATURITY_DATE_FIELD
import com.fioalpha.poc.domain.RATE_FIELD
import com.fioalpha.poc.form.R
import com.fioalpha.poc.form.databinding.InvestimentActivityBinding
import com.fioalpha.poc.form.presentation.result.FORM_EXTRA
import com.fioalpha.poc.form.presentation.result.ResultInvestCalculateActivity
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class InvestedActivity : AppCompatActivity() {

    private val viewModel: InvestedViewModel by inject()

    private val viewBindings by viewBinding(InvestimentActivityBinding::inflate)

    private val removeSymbolsCurrency = Regex("[\\sR$]")
    private val removeSymbol = Regex("[%]")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(viewBindings.root)
        setup()
    }

    private fun setup() {
        viewModel.run {
            lifecycleScope.launch { bind().collect { renderState(it.state) } }
        }

        viewBindings.button.setOnClickListener { handlerClickButton() }

        viewBindings.edtInvestedAmount.addTextChangedListener(ValueWatcher(viewBindings.edtInvestedAmount))
        viewBindings.edtRate.addTextChangedListener(PercentageTextWatcher(viewBindings.edtRate))
        viewBindings.edtDate.addTextChangedListener(DateTextWatcher())
    }

    private fun renderState(state: InvestedState) {
        when (state) {
            is InvestedState.ErrorForms -> handlerErrorForm(state.errors)
            InvestedState.Idle -> {}
            is InvestedState.Success -> {
                Intent(this, ResultInvestCalculateActivity::class.java)
                    .apply { putExtra(FORM_EXTRA, state.data) }
                    .also { startActivity(it) }
            }
        }
        viewModel.handle(InvestedInteraction.IDLE)
    }

    private fun handlerClickButton() {
        viewModel.handle(InvestedInteraction.ValidatedForm(
                FormData(
                        viewBindings.edtInvestedAmount.convertToDouble(removeSymbolsCurrency),
                        viewBindings.edtRate.clearCharacters(removeSymbol),
                        viewBindings.edtDate.convertDate()
                )
        )
        )
    }

    private fun handlerErrorForm(errors: List<String>) {
        errors.forEach {
                when (it) {
                    INVESTED_AMOUNT_FIELD -> viewBindings.edtInvestedAmount.error = getString(R.string.erro_fields)
                    RATE_FIELD -> viewBindings.edtRate.error = getString(R.string.erro_fields)
                    MATURITY_DATE_FIELD -> viewBindings.edtDate.error = getString(R.string.erro_fields)
                }
            }
    }
}





inline fun <T : ViewBinding> AppCompatActivity.viewBinding(
    crossinline binder: (LayoutInflater) -> T
) = lazy(LazyThreadSafetyMode.NONE) { binder.invoke(layoutInflater) }
