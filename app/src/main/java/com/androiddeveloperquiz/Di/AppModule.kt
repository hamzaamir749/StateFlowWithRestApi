package com.androiddeveloperquiz.Di

import android.content.Context
import com.androiddeveloperquiz.Adapters.EventsAdapter
import com.androiddeveloperquiz.Network.ApiInterface
import com.androiddeveloperquiz.R
import com.androiddeveloperquiz.Repositories.EventsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofitInterface(@ApplicationContext context: Context): ApiInterface {
        return Retrofit.Builder().baseUrl(context.getString(R.string.BASE_URL))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInterface::class.java)
    }

    @Provides
    @Singleton
    fun provideEventsRepository(apiInterface: ApiInterface): EventsRepository {
        return EventsRepository(apiInterface)
    }

    @Provides
    @Singleton
    fun provideEventsAdapter(): EventsAdapter {
        return EventsAdapter()
    }

}