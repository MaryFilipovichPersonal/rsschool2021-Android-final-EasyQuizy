package com.rsshool2021.android.finaltask.easyquizy.presentation.quiz

import com.rsshool2021.android.finaltask.easyquizy.presentation.quiz.entity.Quiz

sealed class QuizViewState {
    data class Success(val quiz: Quiz) : QuizViewState()
    data class Error(val errorMessage: String) : QuizViewState()
    object Loading : QuizViewState()
}