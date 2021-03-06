package com.example.currencyConverter.domain.entity.models

import com.google.gson.annotations.SerializedName


data class Valute(
    @SerializedName("ID")
    val id: String,

    @SerializedName("NumCode")
    val numCode: String,

    @SerializedName("CharCode")
    val charCode: String,

    @SerializedName("Nominal")
    val nominal: Long,

    @SerializedName("Name")
    val name: String,

    @SerializedName("Value")
    val value: Double,

    @SerializedName("Previous")
    val previous: Double
) : java.io.Serializable