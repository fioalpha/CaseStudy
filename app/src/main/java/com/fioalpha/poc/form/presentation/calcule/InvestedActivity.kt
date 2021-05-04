package com.fioalpha.poc.form.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.viewbinding.ViewBinding
import com.fioalpha.poc.domain.FormData
import com.fioalpha.poc.domain.INVESTED_AMOUNT_FIELD
import com.fioalpha.poc.domain.MATURITY_DATE_FIELD
import com.fioalpha.poc.domain.RATE_FIELD
import com.fioalpha.poc.form.databinding.InvestimentActivityBinding
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

private const val DATE_PICKER_TAG = "DATE_PICKER"

class InvestedActivity: AppCompatActivity(), DatePickerDialog.OnDateSetListener {

    private val viewModel: InvestedViewModel by inject()

    private val viewBindings by viewBinding(InvestimentActivityBinding::inflate)

    private val datePickerDialog: DatePickerDialog by lazy {
        DatePickerDialog.newInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(viewBindings.root)
        setup()
    }

    override fun onDateSet(view: DatePickerDialog?, year: Int, monthOfYear: Int, dayOfMonth: Int) {
        viewBindings.edtDate.setText("$dayOfMonth/$monthOfYear/$year")
    }

    private fun showDatePicker() {
        datePickerDialog.show(supportFragmentManager, DATE_PICKER_TAG)
    }

    private fun setup() {
        viewModel.run {
            lifecycleScope.launch { bind().collect { renderState(it.state) } }
        }

        viewBindings.tvDate.setOnClickListener { showDatePicker() }
        viewBindings.edtDate.setOnClickListener { showDatePicker() }
        viewBindings.button.setOnClickListener { handlerClickButton() }
    }

    private fun renderState(state: InvestedState) {
        when(state) {
            is InvestedState.ErrorForms -> handlerErrorForm(state.errors)
            InvestedState.Idle -> {}
            is InvestedState.Success -> Toast.makeText(this, "adasd", Toast.LENGTH_SHORT).show()
        }
        viewModel.handle(InvestedInteraction.IDLE)
    }

    private fun handlerClickButton() {
        viewModel.handle(InvestedInteraction.ValidatedForm(
                FormData(
                        viewBindings.edtInvestedAmount.text?.toString()?.toIntOrNull()?: 0,
                        viewBindings.edtRate.text?.toString()?.toIntOrNull()?: 0,
                        viewBindings.edtDate.text?.toString().orEmpty()
                ))
        )
    }

    private fun handlerErrorForm(errors: List<String>) {
        errors.forEach {
                when(it) {
                    INVESTED_AMOUNT_FIELD -> viewBindings.edtInvestedAmount.error = "Campo errado"
                    RATE_FIELD -> viewBindings.edtRate.error = "CAMPO ERRODO"
                    MATURITY_DATE_FIELD -> viewBindings.edtDate.error = "CAMPO ERROR"
                }
            }
    }

}

inline fun <T : ViewBinding> AppCompatActivity.viewBinding(
        crossinline binder: (LayoutInflater) -> T
) = lazy(LazyThreadSafetyMode.NONE) { binder.invoke(layoutInflater) }
