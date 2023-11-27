package com.example.interrapidisimo.di

import android.content.Context
import androidx.room.Room
import com.example.interrapidisimo.data.Constant
import com.example.interrapidisimo.dataBase.TablesDatabase
import com.example.interrapidisimo.repository.ITableRepository
import com.example.interrapidisimo.repository.TableEntityRepository
import com.example.interrapidisimo.services.IApiClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constant.URL_API)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideApiClient(retrofit: Retrofit): IApiClient{
        return retrofit.create(IApiClient::class.java)
    }

    @Singleton
    @Provides
    fun provideRoom(
        @ApplicationContext context: Context
    ): TablesDatabase {
            return Room.databaseBuilder(
                context,
                TablesDatabase::class.java,
                Constant.DATABASE_NAME
            ).build()
    }

    @Provides
    @Singleton
    fun provideRepository(repository: TableEntityRepository): ITableRepository {
        return repository
    }


}