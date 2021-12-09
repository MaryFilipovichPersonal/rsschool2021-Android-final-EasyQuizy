package com.rsshool2021.android.finaltask.easyquizy.presentation.result

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rsshool2021.android.finaltask.easyquizy.domain.result.usecase.SaveBestResultUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ResultViewModel @Inject constructor(private val saveBestResultUseCase: SaveBestResultUseCase) :
    ViewModel() {

    fun saveBestResult(result: Int) {
        viewModelScope.launch {
            saveBestResultUseCase.execute(result)
        }
    }

}