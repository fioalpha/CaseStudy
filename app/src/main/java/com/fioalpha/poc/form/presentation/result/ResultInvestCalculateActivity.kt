package com.fioalpha.poc.form.presentation.result

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.fioalpha.poc.component.extension.hide
import com.fioalpha.poc.component.extension.show
import com.fioalpha.poc.component.view.ResultInvestmentHeadModel
import com.fioalpha.poc.component.view.model.ResultInvestmentBottomModel
import com.fioalpha.poc.component.view.model.ResultInvestmentMediumModel
import com.fioalpha.poc.domain.FormData
import com.fioalpha.poc.domain.model.Investment
import com.fioalpha.poc.form.databinding.ResultInvestmentActivityBinding
import com.fioalpha.poc.form.presentation.calcule.viewBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject


const val FORM_EXTRA = "FORM"
class ResultInvestCalculateActivity : AppCompatActivity() {

    private val viewBindings by viewBinding(ResultInvestmentActivityBinding::inflate)

    private val formData: FormData by lazy {
        intent.getSerializableExtra(FORM_EXTRA) as FormData
    }

    private val resultInvestViewModel: ResultInvestViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(viewBindings.root)

        resultInvestViewModel.run {
            lifecycleScope.launch {
                bind().collect { handleState(it) }
            }
        }

        viewBindings.btnNewSimulator.setOnClickListener { onBackPressed() }
    }

    override fun onStart() {
        super.onStart()
        resultInvestViewModel.handle(
            ResultInteraction.Calculate(
                FormData(formData.investedAmount, formData.rate, formData.maturityDate)
            )
        )

        resultInvestViewModel.bind()
    }

    private fun handleState(state: ResultState) {
        when (state) {
            is ResultState.Error -> showError()
            ResultState.Loader -> showLoader()
            is ResultState.Result -> setData(state.head, state.medium, state.bottom)
            ResultState.Idle -> { }
        }
    }

    private fun showError() {}

    private fun showLoader() {
        viewBindings.groupLoading.show()
        viewBindings.groupData.hide()
    }

    private fun setData(
            head: ResultInvestmentHeadModel,
            medium: ResultInvestmentMediumModel,
            bottom: ResultInvestmentBottomModel
    ) {
        viewBindings.groupData.show()
        viewBindings.groupLoading.hide()
        viewBindings.resultInvestmentHead.bind(head)
        viewBindings.resultInvestmentMedium.bind(medium)
        viewBindings.resultInvestmentBottom.bind(bottom)
    }

}
