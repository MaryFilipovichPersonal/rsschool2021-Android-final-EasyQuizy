package com.rsshool2021.android.finaltask.easyquizy.domain.main

interface AppThemeRepository {

    suspend fun getAppTheme(): String
}