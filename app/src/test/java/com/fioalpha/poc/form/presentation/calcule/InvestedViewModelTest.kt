package com.fioalpha.poc.form.presentation.calcule

import com.fioalpha.poc.domain.model.FormData
import com.fioalpha.poc.domain.ValidatedForm
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class InvestedViewModelTest {

    @Mock
    lateinit var validatedForm: ValidatedForm

    val dispatcher = TestCoroutineDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher)
        MockitoAnnotations.openMocks(this)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun test() {
        runBlocking(Dispatchers.Main) {
            val model = InvestedViewModel(validatedForm)
            model.handle(InvestedInteraction.IDLE)
            assertEquals(model.bind().value, State(InvestedState.Idle))
        }
    }

    @Test
    fun `when called handle With form okay Then return State success`() {
        val formMock = FormData(1, 1, "")
        runBlocking(Dispatchers.Main) {
            val model = InvestedViewModel(validatedForm)
            model.handle(InvestedInteraction.ValidatedForm(formMock))
            assertEquals(model.bind().value, State(InvestedState.Success(formMock)))
        }
    }

    @Test
    fun `when called handle With form error Then return State error`() {
        val formMock = FormData(1, 1, "")
        val errorMock = listOf("ERROR_1", "ERROR_2")
        `when`(validatedForm.isValidated(formMock)).thenReturn(errorMock)

        runBlocking(Dispatchers.Main) {
            val model = InvestedViewModel(validatedForm)
            model.handle(InvestedInteraction.ValidatedForm(formMock))
            assertEquals(model.bind().value, State(InvestedState.ErrorForms(errorMock)))
        }
    }
}
