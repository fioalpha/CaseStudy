package com.fioalpha.poc.form

import android.app.Application
import com.fioalpha.poc.data.di.dataModuleDI
import com.fioalpha.poc.di.domainDI
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
            modules(
                appModule,
                dataModuleDI,
                domainDI
            )
        }
    }
}

val appModule = module {
    single<ValidatedForm> {
        ValidatedFormImpl(InvestedAmountFieldUseCase(), RateFieldUseCase(), MaturityDateFieldUseCase())
    }
    viewModel { InvestedViewModel(get()) }
    viewModel { ResultInvestViewModel(get()) }
}