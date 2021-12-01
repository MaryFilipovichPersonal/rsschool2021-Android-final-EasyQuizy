package com.rsshool2021.android.finaltask.easyquizy.data.common.mappers

import com.rsshool2021.android.finaltask.easyquizy.data.quiz.remote.dto.QuestionResponse
import com.rsshool2021.android.finaltask.easyquizy.domain.quiz.entity.QuestionDomain

object ResponseToQuestionDomainMapper : Mapper<QuestionResponse, QuestionDomain> {

    override fun map(input: QuestionResponse): QuestionDomain =
        with(input) {
            QuestionDomain(
                question = question,
                correctAnswer = correctAnswer,
                incorrectAnswers = incorrectAnswers
            )
        }

}