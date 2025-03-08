package com.example.gestiotech_v3.di

import com.example.gestiotech_v3.data.repository.ClientRepositoryFirestore
import com.example.gestiotech_v3.data.repository.IClientRepository
import com.example.gestiotech_v3.data.repository.IUserRepository
import com.example.gestiotech_v3.data.repository.UserRepositoryFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providesClientRepository(): IClientRepository  {
        return ClientRepositoryFirestore()
    }


    @Singleton
    @Provides
    fun providesUserRepository(): IUserRepository {
        return UserRepositoryFirestore()
    }
}