package com.rsshool2021.android.finaltask.easyquizy.domain.main.usecase

import com.rsshool2021.android.finaltask.easyquizy.domain.main.AppThemeRepository
import javax.inject.Inject

class GetAppThemeUseCase @Inject constructor(private val repository: AppThemeRepository) {

    suspend fun execute(): String = repository.getAppTheme()

}