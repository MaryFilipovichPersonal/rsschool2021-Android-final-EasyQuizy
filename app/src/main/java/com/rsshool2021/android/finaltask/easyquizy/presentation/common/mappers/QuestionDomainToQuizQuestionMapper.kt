package com.rsshool2021.android.finaltask.easyquizy.presentation.common.mappers

import com.rsshool2021.android.finaltask.easyquizy.data.common.mappers.Mapper
import com.rsshool2021.android.finaltask.easyquizy.domain.quiz.entity.QuestionDomain
import com.rsshool2021.android.finaltask.easyquizy.presentation.quiz.entity.Question

object QuestionDomainToQuizQuestionMapper : Mapper<QuestionDomain, Question> {

    override fun map(input: QuestionDomain): Question =
        with(input) {
            Question(
                question = question,
                correctAnswer = correctAnswer,
                allAnswers = getAllAnswers(this)
            )
        }

    private fun getAllAnswers(questionDomain: QuestionDomain): List<String> =
        with(questionDomain) {
            (incorrectAnswers as ArrayList).apply {
                add(correctAnswer)
                shuffle()
            }
        }
}