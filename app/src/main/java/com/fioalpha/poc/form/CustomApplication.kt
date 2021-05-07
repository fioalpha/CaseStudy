package com.fioalpha.poc.form

import android.app.Application
import com.fioalpha.poc.domain.*
import com.fioalpha.poc.domain.model.Investment
import com.fioalpha.poc.domain.model.InvestmentParameter
import com.fioalpha.poc.domain.repository.Repository
import com.fioalpha.poc.form.presentation.calcule.InvestedViewModel
import com.fioalpha.poc.form.presentation.result.ResultInvestViewModel
import kotlinx.coroutines.delay
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class CustomApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@CustomApplication)
            modules(appModule)
        }
    }
}

val appModule = module {
    single<ValidatedForm> {
        ValidatedFormImpl(InvestedAmountFieldUseCase(), RateFieldUseCase(), MaturityDateFieldUseCase())
    }
    single<CalculateInvestedUseCase> {
        CalculateInvestedUseCaseImpl(object: Repository{
            override suspend fun fetchInvestment(formData: FormData): Investment {
                delay(300)
                return Investment(
                    1.0,
                    1.0,
                    1.0,0.0,
                    0.0,
                    InvestmentParameter(
                        0.0,
                        true,
                        0,
                        "",
                        0,
                        0.0,
                        0.0
                    ),
                    0.0,
                    0.0,
                    0.0,
                    0.0,
                    0.0,
                    0.0,
                )
            }

        })
    }
    viewModel { InvestedViewModel(get()) }
    viewModel { ResultInvestViewModel(get()) }
}