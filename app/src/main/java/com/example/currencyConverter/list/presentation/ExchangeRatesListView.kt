package com.example.currencyConverter.list.presentation

import com.example.currencyConverter.domain.entity.models.ExchangeRates
import com.example.currencyConverter.domain.entity.models.Valute
import com.example.currencyConverter.presentation.base.BaseView

interface ExchangeRatesListView : BaseView {
    fun showExchangeRates(exchangeRates: ExchangeRates)
    fun navigateToValuteConverter(valute: Valute)
    fun showToast(message: String)
}