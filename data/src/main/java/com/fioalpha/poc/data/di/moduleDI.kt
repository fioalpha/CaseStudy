package com.fioalpha.poc.data.di

import com.fioalpha.poc.data.networking.RepositoryImpl
import com.fioalpha.poc.data.networking.RetrofitBuilder
import com.fioalpha.poc.data.networking.model.RetrofitService
import com.fioalpha.poc.domain.repository.Repository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module

val dataModuleDI = module {
    single<Repository> {
        RepositoryImpl(retrofitService = get())
    }

    single<RetrofitService> {
        RetrofitBuilder.invoke(
            url = get(),
            client = get()
        ).create(RetrofitService::class.java)
    }

    single<OkHttpClient> {

        val logger = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        OkHttpClient.Builder()
            .addInterceptor(logger)
            .build()
    }

    single { "https://24fff3b4-a437-4d0b-9820-4ead8f2ecf58.mock.pstmn.io/" }
}
