package com.rsshool2021.android.finaltask.easyquizy.domain.quiz.usecase

import com.rsshool2021.android.finaltask.easyquizy.domain.quiz.entity.Question
import com.rsshool2021.android.finaltask.easyquizy.domain.quiz.QuizRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class GetQuizQuestionsUseCase @Inject constructor(private val quizRepository: QuizRepository) {

    fun execute(): Flow<List<Question>> = quizRepository.getQuizQuestions()
}