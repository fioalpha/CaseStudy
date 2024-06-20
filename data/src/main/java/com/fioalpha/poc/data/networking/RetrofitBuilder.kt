package com.fioalpha.poc.data.networking

import com.google.gson.Gson
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {
    operator fun invoke(url: String, client: OkHttpClient): Retrofit =
        with(Retrofit.Builder()) {
            baseUrl(url)
            client(client)
            addConverterFactory(GsonConverterFactory.create(Gson()))
            build()
        }
}
