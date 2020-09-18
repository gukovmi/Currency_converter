package com.example.currencyConverter.converter.presentation

import com.example.currencyConverter.domain.entity.models.Valute
import com.example.currencyConverter.presentation.base.BaseView

interface ConverterView: BaseView {
    fun initView(valute: Valute)
    fun showConvertResult(result: Double)
}