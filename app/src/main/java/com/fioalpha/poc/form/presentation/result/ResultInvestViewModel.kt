package com.fioalpha.poc.form.presentation.result

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fioalpha.poc.domain.CalculateInvestedUseCase
import com.fioalpha.poc.domain.FormData
import com.fioalpha.poc.form.presentation.result.transforms.transformBottom
import com.fioalpha.poc.form.presentation.result.transforms.transformHead
import com.fioalpha.poc.form.presentation.result.transforms.transformMedium
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import java.lang.Exception

class ResultInvestViewModel(
    private val calculate: CalculateInvestedUseCase
): ViewModel() {
    private val states: MutableStateFlow<ResultState> = MutableStateFlow(ResultState.Idle)
    private val interactions = Channel<ResultInteraction>(Channel.UNLIMITED)

    init {
        viewModelScope.launch {
            interactions.consumeAsFlow().collect {
                when (it) {
                    is ResultInteraction.Calculate -> calculateInvestment(it.form)
                }
            }
        }
    }

    fun bind() = states.asStateFlow()

    fun handle(interaction: ResultInteraction) = viewModelScope.launch {
        interactions.send(interaction)
    }

    private suspend fun calculateInvestment(data: FormData) {
        states.value = ResultState.Loader
        try {
            val result = calculate.execute(data)
            states.value = ResultState.Result(
                    result.transformHead(),
                    result.transformMedium(),
                    result.transformBottom()
            )
        } catch (e: Exception) {
            states.value = ResultState.Error(e.message.orEmpty())
        }
    }
}