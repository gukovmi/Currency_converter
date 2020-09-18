package com.example.currencyConverter.list.domain

import com.example.currencyConverter.domain.entity.models.ExchangeRates
import io.reactivex.Single


interface ExchangeRatesListModel {
    fun getExchangeRatesList(): Single<ExchangeRates>
    fun updateExchangeRates(): Single<ExchangeRates>
}

class ExchangeRatesListModelImpl(
    private val getExchangeRatesListUseCase: GetExchangeRatesListUseCase,
    private val updateExchangeRatesUseCase: UpdateExchangeRatesUseCase
) : ExchangeRatesListModel {
    override fun getExchangeRatesList(): Single<ExchangeRates> =
        getExchangeRatesListUseCase()

    override fun updateExchangeRates(): Single<ExchangeRates> =
        updateExchangeRatesUseCase()
}
