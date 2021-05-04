package com.fioalpha.poc.form

import android.app.Application
import com.fioalpha.poc.domain.*
import com.fioalpha.poc.form.presentation.InvestedViewModel
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
    viewModel { InvestedViewModel(get()) }
}