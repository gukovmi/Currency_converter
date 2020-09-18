package com.example.currencyConverter.converter.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.currencyConverter.R
import com.example.currencyConverter.domain.entity.models.Valute
import kotlinx.android.synthetic.main.activity_converter.*
import java.lang.Exception

class ConverterActivity : AppCompatActivity(), ConverterView {
    private val presenter= ConverterPresenterImpl()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_converter)

        val valute = intent.getSerializableExtra("valute") as Valute

        presenter.attachView(this)
        presenter.onViewAttached(valute)
    }

    override fun initView(valute: Valute) {
        resultValuteTextView.text=valute.name
        convertButton.setOnClickListener {
            try {
                val initialAmount=initialAmountEditText.text.toString().toDouble()
                presenter.convert(initialAmount)
            } catch (e:Exception) {
                Toast.makeText(this, "Поля заполнены некорректно!", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun showConvertResult(result: Double) {
        resultAmountTextView.text=result.toString()
        Log.e("result: ", result.toString())
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }
}