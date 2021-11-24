package com.rsshool2021.android.finaltask.easyquizy.domain.quiz

import com.rsshool2021.android.finaltask.easyquizy.domain.quiz.entity.Question
import kotlinx.coroutines.flow.Flow

interface QuizRepository {

    fun getQuizQuestions(): Flow<List<Question>>
}
