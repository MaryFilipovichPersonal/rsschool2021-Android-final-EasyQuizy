package com.rsshool2021.android.finaltask.easyquizy.domain.result.usecase

import com.rsshool2021.android.finaltask.easyquizy.domain.result.ResultRepository
import javax.inject.Inject

class SaveBestResultUseCase @Inject constructor(private val repository: ResultRepository) {

    suspend fun execute(result: Int) {
        repository.saveBestResult(result)
    }

}