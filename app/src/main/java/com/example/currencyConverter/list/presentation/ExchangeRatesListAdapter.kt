package com.example.currencyConverter.list.presentation

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.currencyConverter.R
import com.example.currencyConverter.domain.entity.models.ExchangeRates
import com.example.currencyConverter.domain.entity.models.Valute
import kotlinx.android.synthetic.main.item_valute.view.*

typealias OnValuteItemClick = (Valute) -> Unit

class ExchangeRatesListAdapter(
    private val context: Context,
    private val exchangeRates: ExchangeRates,
    private val onValuteItemClick: OnValuteItemClick
) : RecyclerView.Adapter<ExchangeRatesListAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindValute(valute: Valute, onValuteItemClick: OnValuteItemClick) {
            itemView.apply {
                itemValuteName.text = valute.name
                itemValuteValue.text = "Курс: " + valute.value.toString() + " руб."
                itemValuteNumChar.text = "Обозначение: " + valute.charCode
                setOnClickListener { onValuteItemClick(valute) }
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ExchangeRatesListAdapter.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_valute, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return exchangeRates.valute.size
    }

    override fun onBindViewHolder(holder: ExchangeRatesListAdapter.ViewHolder, position: Int) {
        val exchangeRatesList = mutableListOf<Valute>()
        exchangeRates.valute.forEach {
            exchangeRatesList.add(it.value)
        }
        holder.bindValute(exchangeRatesList[position], onValuteItemClick)
    }
}