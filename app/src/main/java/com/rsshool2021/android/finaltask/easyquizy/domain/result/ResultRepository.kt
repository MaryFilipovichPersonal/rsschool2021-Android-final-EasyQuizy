package com.rsshool2021.android.finaltask.easyquizy.domain.result

interface ResultRepository {

    suspend fun getBestResult(): Int

    suspend fun saveBestResult(result: Int)

}