package com.fioalpha.poc.domain


import com.fioalpha.poc.domain.model.FormData
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class ValidatedFormTest {

    private lateinit var validatedForm: ValidatedForm

    @Mock
    private lateinit var investedAmountFieldUseCase: IsFieldFormValidated<Int>

    @Mock
    private lateinit var investedAmountFieldUseCase1: IsFieldFormValidated<Int>

    @Mock
    private lateinit var investedAmountFieldUseCase2: IsFieldFormValidated<String>

    private val formDataMock = FormData(1, 2, "2022-01-01")
    private val formDataMock1 = FormData(0, 0, "20220101")

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        validatedForm = ValidatedFormImpl(
                investedAmountFieldUseCase,
                investedAmountFieldUseCase1,
                investedAmountFieldUseCase2
        )
    }

    @Test
    fun `when called isValidated Without error Then return list empty` () {
        whenever(investedAmountFieldUseCase.isValidated(1)).thenReturn(false)
        whenever(investedAmountFieldUseCase1.isValidated(1)).thenReturn(false)
        whenever(investedAmountFieldUseCase2.isValidated("2022-01-01")).thenReturn(false)
        val result = validatedForm.isValidated(formDataMock)
        assert(result.isEmpty())

    }

    @Test
    fun `when called isValidated With error Then return one item error` () {
        whenever(investedAmountFieldUseCase.isValidated(0)).thenReturn(true)
        whenever(investedAmountFieldUseCase1.isValidated(0)).thenReturn(true)
        whenever(investedAmountFieldUseCase2.isValidated("20220101")).thenReturn(true)
        val result = validatedForm.isValidated(formDataMock1)
        assert(result.count() == 3)
    }

}