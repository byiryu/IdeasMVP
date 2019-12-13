package com.byiryu.ideasmvp.di.module

import android.app.Application
import android.content.Context
import com.byiryu.ideasmvp.BuildConfig
import com.byiryu.ideasmvp.api.ApiService
import com.byiryu.ideasmvp.di.scope.ApplicationContext
import com.byiryu.ideasmvp.di.scope.ApplicationScoped
import com.byiryu.ideasmvp.utils.API_BASE_URL
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
class AppModule constructor(application: Application){

    private var application = application

    @Provides
    @ApplicationContext
    fun provideContext() : Context{
        return application
    }

    @Provides
    fun provideApplication() : Application{
        return application
    }

    @Provides
    @ApplicationScoped
    fun getApiService(retrofit: Retrofit) : ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @ApplicationScoped
    fun provideGson() : Gson {
        return GsonBuilder().serializeSpecialFloatingPointValues()
            .serializeNulls()
            .create()
    }

    @Provides
    @ApplicationScoped
    fun provideRetrofit(okHttpClient: OkHttpClient, gson: Gson) : Retrofit {
        var builder = Retrofit.Builder()

        builder.baseUrl(API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)

        return builder.build()
    }

    @Provides
    @ApplicationScoped
    fun provideClient(httpLoggingInterceptor: HttpLoggingInterceptor) : OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(20000, TimeUnit.MILLISECONDS)
            .connectTimeout(10000, TimeUnit.MILLISECONDS)
            .retryOnConnectionFailure(true)
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }

    @Provides
    @ApplicationScoped
    fun provideLogger() : HttpLoggingInterceptor {
        var httpLoggingInterceptor = HttpLoggingInterceptor()

        if(BuildConfig.DEBUG)
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        return httpLoggingInterceptor
    }



}