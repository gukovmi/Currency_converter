package com.example.currencyConverter.domain.entity.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.currencyConverter.domain.entity.models.ExchangeRates

@Database(entities = [(ExchangeRates::class)], version = 1, exportSchema = false)
@TypeConverters(ValuteMapConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun exchangeRatesDao(): ExchangeRatesDao
}