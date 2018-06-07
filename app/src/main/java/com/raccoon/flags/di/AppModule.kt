package com.raccoon.flags.di

import android.app.Application
import android.content.Context
import com.google.gson.Gson
import com.raccoon.flags.api.ApiService
import com.raccoon.flags.schedulers.ISchedulers
import com.raccoon.flags.schedulers.WorkSchedulers
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun provideContext(application: Application): Context = application.applicationContext

    @Provides
    @Singleton
    fun provideGson(): Gson = Gson()

    @Provides
    @Singleton
    fun provideScheduler(): ISchedulers = WorkSchedulers

    @Provides
    @Singleton
    fun provideApiService(): ApiService =
            Retrofit.Builder()
                    .baseUrl(ApiService.apiEndpoint)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()
                    .create(ApiService::class.java)

}
