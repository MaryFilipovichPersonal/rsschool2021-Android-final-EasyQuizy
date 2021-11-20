package com.rsshool2021.android.finaltask.easyquizy.domain.models

data class Question(
    val question: String,
    val correctAnswer: String,
    val incorrectAnswers: ArrayList<String>
)
