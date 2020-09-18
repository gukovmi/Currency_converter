package com.example.currencyConverter.presentation.base

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

open class BasePresenter<T : BaseView>  {
    var view: T? = null
    private val compositeDisposable = CompositeDisposable()

    fun attachView(view: T) {
        this.view = view
        onViewAttached()
    }

    open fun onViewAttached() {

    }

    fun onDestroy() {
        view = null
        compositeDisposable.clear()
    }

    fun Disposable.untilDestroy() = compositeDisposable.add(this)
}