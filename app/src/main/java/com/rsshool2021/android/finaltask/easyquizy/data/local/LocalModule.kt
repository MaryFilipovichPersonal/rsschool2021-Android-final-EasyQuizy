package com.rsshool2021.android.finaltask.easyquizy.data.local

import com.rsshool2021.android.finaltask.easyquizy.data.common.PreferenceManager
import com.rsshool2021.android.finaltask.easyquizy.data.common.ResourceManager
import com.rsshool2021.android.finaltask.easyquizy.data.common.di.CommonModule
import com.rsshool2021.android.finaltask.easyquizy.data.local.repository.AppThemeRepositoryImpl
import com.rsshool2021.android.finaltask.easyquizy.domain.main.AppThemeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module(includes = [CommonModule::class])
@InstallIn(SingletonComponent::class)
class LocalModule {

    @Singleton
    @Provides
    fun provideAppThemeRepository(
        prefsManager: PreferenceManager,
        resourceManager: ResourceManager
    ): AppThemeRepository =
        AppThemeRepositoryImpl(prefsManager, resourceManager)
}