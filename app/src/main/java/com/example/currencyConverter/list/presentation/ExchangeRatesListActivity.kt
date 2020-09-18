package com.example.currencyConverter.list.presentation

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.currencyConverter.R
import com.example.currencyConverter.converter.presentation.ConverterActivity
import com.example.currencyConverter.domain.entity.models.ExchangeRates
import com.example.currencyConverter.domain.entity.models.Valute
import com.example.currencyConverter.presentation.base.BaseView
import kotlinx.android.synthetic.main.activity_exchange_rates_list.*

class ExchangeRatesListActivity : AppCompatActivity(), ExchangeRatesListView {
    private val presenter=ExchangeRatesListPresenterImpl()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exchange_rates_list)

        presenter.attachView(this)

        updateButton.setOnClickListener { presenter.updateExchangeRates() }
    }

    override fun showExchangeRates(exchangeRates: ExchangeRates) {
        val exchangeRatesListAdapter = ExchangeRatesListAdapter(
            this,
            exchangeRates,
            {valute: Valute -> presenter.onValuteItemClick(valute)})
        exchangeRatesListRecyclerView.layoutManager= LinearLayoutManager(this)
        exchangeRatesListRecyclerView.adapter=exchangeRatesListAdapter
    }

    override fun navigateToValuteConverter(valute: Valute) {
        val intent= Intent(this, ConverterActivity::class.java)
        intent.putExtra("valute", valute)
        startActivity(intent)
        Log.e("Valute name:", valute.name)
    }

    override fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }
}