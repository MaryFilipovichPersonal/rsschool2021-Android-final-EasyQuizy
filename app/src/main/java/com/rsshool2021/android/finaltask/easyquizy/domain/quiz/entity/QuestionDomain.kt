package com.rsshool2021.android.finaltask.easyquizy.domain.quiz.entity

data class QuestionDomain(
    val question: String,
    val correctAnswer: String,
    val incorrectAnswers: List<String>
)
