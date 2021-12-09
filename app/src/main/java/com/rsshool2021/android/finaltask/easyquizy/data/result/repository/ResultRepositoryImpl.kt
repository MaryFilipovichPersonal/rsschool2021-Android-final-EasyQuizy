package com.rsshool2021.android.finaltask.easyquizy.data.result.repository

import com.rsshool2021.android.finaltask.easyquizy.R
import com.rsshool2021.android.finaltask.easyquizy.data.common.PreferenceManager
import com.rsshool2021.android.finaltask.easyquizy.data.common.ResourceManager
import com.rsshool2021.android.finaltask.easyquizy.domain.result.ResultRepository
import javax.inject.Inject

class ResultRepositoryImpl @Inject constructor(
    private val prefsManager: PreferenceManager,
    private val resourceManager: ResourceManager
) : ResultRepository {

    override suspend fun getBestResult(): Int {
        val key = resourceManager.getString(R.string.key_result)
        return prefsManager.get(key, -1)
    }

    override suspend fun saveBestResult(result: Int) {
        if (getBestResult() < result) {
            val key = resourceManager.getString(R.string.key_result)
            prefsManager.put(key, result)
        }
    }
}