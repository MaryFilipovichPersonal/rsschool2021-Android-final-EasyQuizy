package com.rsshool2021.android.finaltask.easyquizy.data.quiz.repository

import com.rsshool2021.android.finaltask.easyquizy.data.common.mappers.ResponseToQuestionMapper
import com.rsshool2021.android.finaltask.easyquizy.data.quiz.remote.api.QuizApi
import com.rsshool2021.android.finaltask.easyquizy.domain.quiz.entity.Question
import com.rsshool2021.android.finaltask.easyquizy.domain.quiz.QuizRepository
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class QuizRepositoryImpl @Inject constructor (private val quizApi: QuizApi) : QuizRepository {

    override fun getQuizQuestions(): Flow<List<Question>> =
        flow {
            val response = quizApi.getQuestions()
            emit(
                with(response){
                    when(responseCode) {
                        0 -> questionResponses.map { ResponseToQuestionMapper.map(it) }
                        else -> emptyList()
                    }
                }
            )
        }

}