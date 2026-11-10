package com.yeudaby.bengate.client.di

import android.content.Context
import com.yeudaby.bengate.client.core.dpm.DeviceManager
import com.yeudaby.bengate.client.core.dpm.DeviceManagerImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class BpmModule {

    @Provides
    @Singleton
    fun provideDeviceManager(
        @ApplicationContext context: Context
    ): DeviceManager {
        return DeviceManagerImpl(
            context
        )
    }

}