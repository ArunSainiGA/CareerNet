package com.asp.careernettest

import android.app.Application

class CareerNetApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        DependencyInjector.init(this)
    }
}