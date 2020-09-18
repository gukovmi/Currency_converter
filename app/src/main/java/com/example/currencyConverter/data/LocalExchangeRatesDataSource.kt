package com.example.currencyConverter.data

import com.example.currencyConverter.domain.entity.db.AppDatabase
import com.example.currencyConverter.domain.entity.models.ExchangeRates
import io.reactivex.Completable
import io.reactivex.Single


interface LocalExchangeRatesDataSource {
    fun getExchangeRates(): Single<ExchangeRates>
    fun saveExchangeRates(exchangeRates: ExchangeRates): Completable
    fun clearExchangeRates(): Completable
}

class LocalExchangeRatesDataSourceImpl(private val db: AppDatabase) : LocalExchangeRatesDataSource {
    override fun getExchangeRates(): Single<ExchangeRates> =
        db.exchangeRatesDao().getExchangeRates()

    override fun saveExchangeRates(exchangeRates: ExchangeRates): Completable =
        db.exchangeRatesDao().saveExchangeRates(exchangeRates)

    override fun clearExchangeRates(): Completable =
        db.exchangeRatesDao().clearExchangeRates()
}
