package com.rsshool2021.android.finaltask.easyquizy.domain.quiz.entity

data class Question(
    val question: String,
    val correctAnswer: String,
    val incorrectAnswers: ArrayList<String>
)
