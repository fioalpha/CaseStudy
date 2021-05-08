package com.fioalpha.poc.form.presentation.calcule

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fioalpha.poc.domain.model.FormData
import com.fioalpha.poc.domain.ValidatedForm
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch

class InvestedViewModel(
    private val validatedForm: ValidatedForm
) : ViewModel() {

    private val states: MutableStateFlow<State> = MutableStateFlow(State(InvestedState.Idle))
    private val interactions = Channel<InvestedInteraction>(Channel.UNLIMITED)

    init {
        viewModelScope.launch {
            interactions.consumeAsFlow().collect(::interactionHandler)
        }
    }

    fun bind() = states.asStateFlow()

    fun handle(interaction: InvestedInteraction) = viewModelScope.launch {
        interactions.send(interaction)
    }

    private fun validatedForm(data: FormData) {
        val validatedResult = validatedForm.isValidated(data)
        when {
            validatedResult.isEmpty() -> states.value = State(state = InvestedState.Success(data))
            else -> states.value = State(InvestedState.ErrorForms(validatedResult))
        }
    }

    private fun interactionHandler(interaction: InvestedInteraction) {
        when (interaction) {
            is InvestedInteraction.ValidatedForm -> validatedForm(interaction.data)
            InvestedInteraction.IDLE -> states.value = State(InvestedState.Idle)
        }
    }
}
