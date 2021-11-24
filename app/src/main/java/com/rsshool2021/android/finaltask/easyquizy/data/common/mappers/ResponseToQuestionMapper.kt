package com.rsshool2021.android.finaltask.easyquizy.data.common.mappers

import com.rsshool2021.android.finaltask.easyquizy.data.quiz.remote.dto.QuestionResponse
import com.rsshool2021.android.finaltask.easyquizy.domain.quiz.entity.Question

object ResponseToQuestionMapper : Mapper<QuestionResponse, Question> {

    override fun map(value: QuestionResponse): Question =
        with(value) {
            Question(
                question = question,
                correctAnswer = correctAnswer,
                incorrectAnswers = incorrectAnswers
            )
        }

}