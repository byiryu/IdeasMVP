package com.byiryu.ideasmvp

import android.app.Application
import com.byiryu.ideasmvp.di.component.AppComponent
import com.byiryu.ideasmvp.di.component.DaggerAppComponent
import com.byiryu.ideasmvp.di.module.AppModule
import pyxis.uzuki.live.nyancat.NyanCatGlobal
import pyxis.uzuki.live.nyancat.config.LoggerConfig
import pyxis.uzuki.live.nyancat.config.TriggerTiming

class IdeasApplication : Application(){

    lateinit var mAppComponent : AppComponent
    lateinit var loggerConfig: LoggerConfig

    @Override
    override fun onCreate() {
        super.onCreate()

        mAppComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()

        mAppComponent.inject(this)

        loggerConfig = LoggerConfig(packageName, true, TriggerTiming.ALL)
        NyanCatGlobal.breed(loggerConfig)

    }

    fun getComponent() : AppComponent{
        return mAppComponent
    }

    fun setComponent(appComponent: AppComponent) {
        mAppComponent = appComponent
    }

}