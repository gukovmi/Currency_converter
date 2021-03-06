package com.example.currencyConverter.list.domain

import com.example.currencyConverter.data.ExchangeRatesRepository
import com.example.currencyConverter.domain.entity.models.ExchangeRates
import io.reactivex.Single

class UpdateExchangeRatesUseCase(
    private val exchangeRatesRepository: ExchangeRatesRepository
) {
    operator fun invoke(): Single<ExchangeRates> =
        exchangeRatesRepository.updateExchangeRates()
}
