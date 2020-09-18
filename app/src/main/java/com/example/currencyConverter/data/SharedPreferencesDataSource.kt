package com.example.currencyConverter.data

import android.content.SharedPreferences
import android.util.Log


interface SharedPreferencesDataSource {
    fun writeTimeLog(newTimeLog: Long)
    fun readTimeLog(): Long
    fun compareTimeLogs(newTimeLog: Long): Boolean
}

class SharedPreferencesDataSourceImpl(private val sharedPref: SharedPreferences) :
    SharedPreferencesDataSource {
    override fun writeTimeLog(timeLog: Long) {
        with(sharedPref.edit()) {
            putLong("time_log", System.currentTimeMillis())
            commit()
        }
    }

    override fun readTimeLog(): Long {
        return sharedPref.getLong("time_log", 0)
    }

    override fun compareTimeLogs(newTimeLog: Long): Boolean {
        val oldTimeLog = readTimeLog()
        Log.e("time_log_old", oldTimeLog.toString())
        Log.e("time_log_new", newTimeLog.toString())
        val difTimeLog = newTimeLog - oldTimeLog
        Log.e("compareTimeLogs()", ((difTimeLog >= 3600000).toString()))
        return difTimeLog >= 3600000
    }
}
