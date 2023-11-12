package com.kwh.dailyq

import android.app.Application
import com.kwh.dailyq.api.ApiService

class App : Application(){

    override fun onCreate() {
        super.onCreate()

        ApiService.init(this)
    }
}