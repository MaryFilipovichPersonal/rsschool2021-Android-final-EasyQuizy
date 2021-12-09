package com.rsshool2021.android.finaltask.easyquizy.presentation.start

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rsshool2021.android.finaltask.easyquizy.domain.result.usecase.GetBestResultUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StartScreenViewModel @Inject constructor(private val getBestResultUseCase: GetBestResultUseCase) :
    ViewModel() {

    private val _bestResult = MutableStateFlow(0)
    val bestResult: StateFlow<Int> get() = _bestResult

    fun getBestResult() {
        viewModelScope.launch {
            _bestResult.value = getBestResultUseCase.execute()
        }
    }

}