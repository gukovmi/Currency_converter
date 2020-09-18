package com.example.currencyConverter

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.currencyConverter.domain.entity.db.AppDatabase

class App : Application() {

    companion object {
        lateinit var instance: App
        lateinit var db: AppDatabase
        fun appContext(): Context = instance.applicationContext
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        db = Room.databaseBuilder(
            appContext(),
            AppDatabase::class.java, "exchange_rates"
        ).build()
    }
}