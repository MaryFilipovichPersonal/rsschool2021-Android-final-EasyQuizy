package com.rsshool2021.android.finaltask.easyquizy.data.quiz.repository

import com.rsshool2021.android.finaltask.easyquizy.data.common.mappers.ResponseToQuestionDomainMapper
import com.rsshool2021.android.finaltask.easyquizy.data.quiz.remote.api.QuizApi
import com.rsshool2021.android.finaltask.easyquizy.domain.quiz.entity.QuestionDomain
import com.rsshool2021.android.finaltask.easyquizy.domain.quiz.QuizRepository
import javax.inject.Inject

private const val SUCCESS_CODE = 0

class QuizRepositoryImpl @Inject constructor(private val quizApi: QuizApi) : QuizRepository {

    override suspend fun getQuizQuestions(): Result<List<QuestionDomain>> =
        runCatching {
            with(quizApi.getQuestions()) {
                when (responseCode) {
                    SUCCESS_CODE -> questionResponses.map { ResponseToQuestionDomainMapper.map(it) }
                    else -> throw Throwable("Server error")
                }
            }
        }
}