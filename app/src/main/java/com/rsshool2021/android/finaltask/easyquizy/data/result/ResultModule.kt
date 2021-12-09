package com.rsshool2021.android.finaltask.easyquizy.data.result

import com.rsshool2021.android.finaltask.easyquizy.data.common.PreferenceManager
import com.rsshool2021.android.finaltask.easyquizy.data.common.ResourceManager
import com.rsshool2021.android.finaltask.easyquizy.data.common.di.CommonModule
import com.rsshool2021.android.finaltask.easyquizy.data.result.repository.ResultRepositoryImpl
import com.rsshool2021.android.finaltask.easyquizy.data.theme.repository.AppThemeRepositoryImpl
import com.rsshool2021.android.finaltask.easyquizy.domain.main.AppThemeRepository
import com.rsshool2021.android.finaltask.easyquizy.domain.result.ResultRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module(includes = [CommonModule::class])
@InstallIn(SingletonComponent::class)
class ResultModule {

    @Singleton
    @Provides
    fun provideResultRepository(
        prefsManager: PreferenceManager,
        resourceManager: ResourceManager
    ): ResultRepository =
        ResultRepositoryImpl(prefsManager, resourceManager)
}