package com.rsshool2021.android.finaltask.easyquizy.presentation.main

import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rsshool2021.android.finaltask.easyquizy.domain.main.usecase.GetAppThemeUseCase
import com.rsshool2021.android.finaltask.easyquizy.presentation.common.mappers.AppThemeMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val getThemeUseCase: GetAppThemeUseCase) :
    ViewModel() {

    private val _appTheme = MutableStateFlow(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
    val appTheme: StateFlow<Int> get() = _appTheme

    fun getAppTheme() {
        viewModelScope.launch {
            val theme = AppThemeMapper.map(getThemeUseCase.execute())
            if (theme != _appTheme.value) _appTheme.value = theme
        }
    }

}