package com.rsshool2021.android.finaltask.easyquizy.presentation.quiz.entity

data class Quiz(
    val questions: List<Question> = emptyList()
) {

    fun calculateScore(): Int =
        questions.filter { question -> question.isCorrect() }.size

    fun getProgress(): Int =
        questions.map { question -> question.isChecked() }.size
}