package com.rsshool2021.android.finaltask.easyquizy.data.local.repository

import com.rsshool2021.android.finaltask.easyquizy.R
import com.rsshool2021.android.finaltask.easyquizy.data.common.PreferenceManager
import com.rsshool2021.android.finaltask.easyquizy.data.common.ResourceManager
import com.rsshool2021.android.finaltask.easyquizy.domain.main.AppThemeRepository
import javax.inject.Inject

class AppThemeRepositoryImpl @Inject constructor(
    private val prefsManager: PreferenceManager,
    private val resourceManager: ResourceManager,
) : AppThemeRepository {

    override suspend fun getAppTheme(): String {
        val key = resourceManager.getString(R.string.key_theme)
        return prefsManager.get(key, "")
    }
}