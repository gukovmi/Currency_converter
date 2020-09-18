package com.example.currencyConverter.converter.domain

import com.example.currencyConverter.domain.entity.models.Valute
import java.math.BigDecimal

interface ConverterModel {
    fun setValute(newValute: Valute)
    fun getValute(): Valute
    fun convert(conversionAmount: Double): Double
}

class ConverterModelImpl : ConverterModel {
    private lateinit var valute: Valute

    override fun setValute(newValute: Valute) {
        valute = newValute
    }

    override fun getValute(): Valute = valute

    override fun convert(conversionAmount: Double): Double {
        val result = conversionAmount / valute.value
        return BigDecimal(result).setScale(2, BigDecimal.ROUND_HALF_UP).toDouble()
    }
}
