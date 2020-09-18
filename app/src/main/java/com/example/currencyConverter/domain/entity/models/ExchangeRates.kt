package com.example.currencyConverter.domain.entity.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.currencyConverter.domain.entity.db.ValuteMapConverter
import com.google.gson.annotations.SerializedName


@Entity(tableName = "exchange_rates")
data class ExchangeRates(
    @SerializedName("Date")
    val date: String,

    @ColumnInfo(name = "previous_date")
    @SerializedName("PreviousDate")
    val previousDate: String,

    @ColumnInfo(name = "previous_url")
    @SerializedName("PreviousURL")
    val previousURL: String,

    @PrimaryKey
    @SerializedName("Timestamp")
    val timestamp: String,

    @TypeConverters(ValuteMapConverter::class)
    @SerializedName("Valute")
    val valute: Map<String, Valute>
) : java.io.Serializable