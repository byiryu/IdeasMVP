package com.byiryu.ideasmvp.di.component

import android.app.Application
import android.content.Context
import com.byiryu.ideasmvp.IdeasApplication
import com.byiryu.ideasmvp.api.ApiService
import com.byiryu.ideasmvp.di.module.AppModule
import com.byiryu.ideasmvp.di.scope.ApplicationContext
import com.byiryu.ideasmvp.di.scope.ApplicationScoped
import dagger.Component


@ApplicationScoped
@Component(modules = [AppModule::class])
interface AppComponent{

    val apiService : ApiService

    fun inject(ideasApplication: IdeasApplication)

    @ApplicationContext
    fun context() : Context

    fun application() : Application
}


