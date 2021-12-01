package com.rsshool2021.android.finaltask.easyquizy.domain.quiz.usecase

import com.rsshool2021.android.finaltask.easyquizy.domain.quiz.QuizRepository
import com.rsshool2021.android.finaltask.easyquizy.domain.quiz.entity.QuestionDomain
import javax.inject.Inject

class GetQuizQuestionsUseCase @Inject constructor(private val quizRepository: QuizRepository) {

    suspend fun execute(): Result<List<QuestionDomain>> = quizRepository.getQuizQuestions()
}