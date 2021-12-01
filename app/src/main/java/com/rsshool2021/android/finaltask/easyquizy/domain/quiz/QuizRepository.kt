package com.rsshool2021.android.finaltask.easyquizy.domain.quiz

import com.rsshool2021.android.finaltask.easyquizy.domain.quiz.entity.QuestionDomain

interface QuizRepository {

    suspend fun getQuizQuestions(): Result<List<QuestionDomain>>
}
