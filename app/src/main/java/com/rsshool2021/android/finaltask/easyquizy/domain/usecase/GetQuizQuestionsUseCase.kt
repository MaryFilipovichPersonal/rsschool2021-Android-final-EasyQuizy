package com.rsshool2021.android.finaltask.easyquizy.domain.usecase

import com.rsshool2021.android.finaltask.easyquizy.domain.models.Question
import com.rsshool2021.android.finaltask.easyquizy.domain.repository.QuizRepository

class GetQuizQuestionsUseCase(private val quizRepository: QuizRepository) {

    fun execute(): ArrayList<Question> = quizRepository.getQuizQuestions()
}