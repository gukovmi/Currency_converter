package com.example.currencyConverter.data

import android.util.Log
import com.example.currencyConverter.domain.entity.models.ExchangeRates
import io.reactivex.Completable
import io.reactivex.Single

interface ExchangeRatesRepository {
    fun getExchangeRates(): Single<ExchangeRates>
    fun updateExchangeRates(): Single<ExchangeRates>
    fun saveExchangeRates(exchangeRates: ExchangeRates): Completable
    fun clearExchangeRates(): Completable
}

class ExchangeRatesRepositoryImpl(
    private val networkExchangeRatesDataSource: NetworkExchangeRatesDataSource,
    private val localExchangeRatesDataSource: LocalExchangeRatesDataSource,
    private val sharedPreferencesDataSource: SharedPreferencesDataSource
) : ExchangeRatesRepository {
    override fun getExchangeRates(): Single<ExchangeRates> {
        if (sharedPreferencesDataSource.compareTimeLogs(System.currentTimeMillis())) {
            return networkExchangeRatesDataSource.getExchangeRates()
                .flatMap {
                    clearExchangeRates()
                        .andThen(saveExchangeRates(it))
                        .andThen(Single.just(it))
                }
                .onErrorResumeNext {
                    localExchangeRatesDataSource.getExchangeRates()
                }
        } else {
            return localExchangeRatesDataSource.getExchangeRates()
                .onErrorResumeNext {
                    networkExchangeRatesDataSource.getExchangeRates()
                        .flatMap {
                            clearExchangeRates()
                                .andThen(saveExchangeRates(it))
                                .andThen(Single.just(it))
                        }
                }
        }
    }

    override fun updateExchangeRates(): Single<ExchangeRates> =
        networkExchangeRatesDataSource.getExchangeRates()
            .flatMap {
                clearExchangeRates()
                    .andThen(saveExchangeRates(it))
                    .andThen(Single.just(it))
            }

    override fun saveExchangeRates(exchangeRates: ExchangeRates): Completable =
        localExchangeRatesDataSource.saveExchangeRates(exchangeRates)
            .doOnComplete {
                sharedPreferencesDataSource.writeTimeLog(System.currentTimeMillis())
                Log.e("DB", "WAS UPDATED")
            }
            .doOnError { Log.e("DB", it.localizedMessage) }

    override fun clearExchangeRates(): Completable =
        localExchangeRatesDataSource.clearExchangeRates()
            .doOnComplete { Log.e("DB", "WAS CLEARED") }
            .doOnError { Log.e("DB", it.localizedMessage) }
}