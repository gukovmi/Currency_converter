package com.example.currencyConverter.list.presentation

import android.util.Log
import com.example.currencyConverter.domain.entity.models.ExchangeRates
import com.example.currencyConverter.domain.entity.models.Valute
import com.example.currencyConverter.list.di.ExchangeRatesListModelFactory
import com.example.currencyConverter.list.domain.ExchangeRatesListModel
import com.example.currencyConverter.presentation.base.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


interface ExchangeRatesListPresenter {
    fun onViewAttached()
    fun onValuteItemClick(valute: Valute)
    fun showExchangeRates(exchangeRates: ExchangeRates)
    fun makeToast(message: String)
    fun getExchangeRates()
    fun updateExchangeRates()
}


class ExchangeRatesListPresenterImpl : BasePresenter<ExchangeRatesListView>(),
    ExchangeRatesListPresenter {
    private lateinit var model: ExchangeRatesListModel

    override fun onViewAttached() {
        model = ExchangeRatesListModelFactory().create()
        getExchangeRates()
    }

    override fun onValuteItemClick(valute: Valute) {
        view?.navigateToValuteConverter(valute)
    }

    override fun showExchangeRates(exchangeRates: ExchangeRates) {
        view?.showExchangeRates(exchangeRates)
    }

    override fun makeToast(message: String) {
        view?.showToast(message)
    }

    override fun getExchangeRates() {
        model.getExchangeRatesList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                showExchangeRates(it)
            }, {
                Log.e("ERROR!!", it.localizedMessage)
                makeToast(it.localizedMessage)
            }).untilDestroy()
    }

    override fun updateExchangeRates() {
        model.updateExchangeRates()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                showExchangeRates(it)
            }, {
                makeToast(it.localizedMessage)
            }).untilDestroy()
    }
}