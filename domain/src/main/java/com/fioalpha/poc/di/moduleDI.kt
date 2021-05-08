package com.fioalpha.poc.di

import com.fioalpha.poc.domain.CalculateInvestedUseCase
import com.fioalpha.poc.domain.CalculateInvestedUseCaseImpl
import com.fioalpha.poc.domain.InvestedAmountFieldUseCase
import com.fioalpha.poc.domain.MaturityDateFieldUseCase
import com.fioalpha.poc.domain.RateFieldUseCase
import com.fioalpha.poc.domain.ValidatedForm
import com.fioalpha.poc.domain.ValidatedFormImpl
import org.koin.dsl.module

val domainDI = module {
    factory<ValidatedForm>(override = true){
        ValidatedFormImpl(
            investedAmountFieldUseCase = InvestedAmountFieldUseCase(),
            rateFieldUseCase = RateFieldUseCase(),
            maturityDateFieldUseCase = MaturityDateFieldUseCase()
        )
    }

    single<CalculateInvestedUseCase>{
        CalculateInvestedUseCaseImpl(
            repository = get()
        )
    }
}
