package com.fioalpha.poc.di

import com.fioalpha.poc.domain.*
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