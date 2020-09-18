package com.example.currencyConverter.domain.entity.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.currencyConverter.domain.entity.models.ExchangeRates
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface ExchangeRatesDao {
    @Query("SELECT *  FROM exchange_rates ORDER BY timestamp DESC LIMIT 1")
    fun getExchangeRates(): Single<ExchangeRates>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun saveExchangeRates(exchangeRates: ExchangeRates): Completable

    @Query("DELETE FROM exchange_rates")
    fun clearExchangeRates(): Completable
}