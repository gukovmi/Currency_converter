package com.example.currencyConverter.converter.presentation

import com.example.currencyConverter.converter.domain.ConverterModelImpl
import com.example.currencyConverter.domain.entity.models.Valute
import com.example.currencyConverter.presentation.base.BasePresenter

interface ConverterPresenter {
    fun onViewAttached(valute: Valute)
    fun setValute(newValute: Valute)
    fun initView(valute: Valute)
    fun convert(initialAmount: Double)
    fun showConvertResult(result: Double)
}

class ConverterPresenterImpl : BasePresenter<ConverterView>(), ConverterPresenter {
    private val converterModel = ConverterModelImpl()

    override fun onViewAttached(valute: Valute) {
        setValute(valute)
        initView(converterModel.getValute())
    }

    override fun setValute(newValute: Valute) {
        converterModel.setValute(newValute)
    }

    override fun convert(initialAmount: Double) {
        showConvertResult(
            converterModel.convert(initialAmount)
        )
    }

    override fun showConvertResult(result: Double) {
        view?.showConvertResult(result)
    }

    override fun initView(valute: Valute) {
        view?.initView(valute)
    }
}