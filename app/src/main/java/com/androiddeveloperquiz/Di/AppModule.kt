package com.androiddeveloperquiz.Di

import com.androiddeveloperquiz.Network.ApiInterface
import com.androiddeveloperquiz.Repositories.EventsRepository
import com.androiddeveloperquiz.Utils.GlobalVariables
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofitInterface(): ApiInterface {
        return Retrofit.Builder().baseUrl(GlobalVariables.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInterface::class.java)
    }

    @Provides
    @Singleton
    fun provideDictionaryRepository(apiInterface: ApiInterface): EventsRepository {
        return EventsRepository(apiInterface)
    }

}