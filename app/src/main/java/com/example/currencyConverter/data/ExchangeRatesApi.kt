package com.example.currencyConverter.data

import com.example.currencyConverter.domain.entity.models.ExchangeRates
import io.reactivex.Single

import retrofit2.http.GET

interface ExchangeRatesApi {
    @GET("/daily_json.js")
    fun getExchangeRates(): Single<ExchangeRates>
}