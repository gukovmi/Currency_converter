package com.example.currencyConverter.list.di

import android.content.Context
import com.example.currencyConverter.App
import com.example.currencyConverter.data.*
import com.example.currencyConverter.data.Constants.BASE_URL
import com.example.currencyConverter.list.domain.ExchangeRatesListModelImpl
import com.example.currencyConverter.list.domain.GetExchangeRatesListUseCase
import com.example.currencyConverter.list.domain.UpdateExchangeRatesUseCase
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ExchangeRatesListModelFactory {
    fun create(): ExchangeRatesListModelImpl {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(client)
            .build()

        val api = retrofit.create(ExchangeRatesApi::class.java)

        val sharedPref = App.appContext().getSharedPreferences(
            "com.example.currencyConverter.prefs",
            Context.MODE_PRIVATE
        )

        val networkExchangeRatesDataSource = NetworkExchangeRatesDataSourceImpl(api)
        val localExchangeRatesDataSource = LocalExchangeRatesDataSourceImpl(App.db)
        val sharedPreferencesDataSource = SharedPreferencesDataSourceImpl(sharedPref)
        val exchangeRatesRepository = ExchangeRatesRepositoryImpl(
            networkExchangeRatesDataSource,
            localExchangeRatesDataSource,
            sharedPreferencesDataSource
        )

        val getExchangeRatesListUseCase = GetExchangeRatesListUseCase(exchangeRatesRepository)
        val updateExchangeRatesUseCase = UpdateExchangeRatesUseCase(exchangeRatesRepository)

        return ExchangeRatesListModelImpl(
            getExchangeRatesListUseCase,
            updateExchangeRatesUseCase
        )
    }
}