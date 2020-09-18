package com.example.currencyConverter.data

import com.example.currencyConverter.domain.entity.models.ExchangeRates
import io.reactivex.Single

interface NetworkExchangeRatesDataSource {
    fun getExchangeRates(): Single<ExchangeRates>
}

class NetworkExchangeRatesDataSourceImpl(private val api: ExchangeRatesApi) :
    NetworkExchangeRatesDataSource {
    override fun getExchangeRates(): Single<ExchangeRates> =
        api.getExchangeRates()
}