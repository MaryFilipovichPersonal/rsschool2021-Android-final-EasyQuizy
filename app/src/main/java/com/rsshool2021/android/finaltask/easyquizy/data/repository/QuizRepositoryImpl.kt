package com.rsshool2021.android.finaltask.easyquizy.data.repository

import com.rsshool2021.android.finaltask.easyquizy.domain.models.Question
import com.rsshool2021.android.finaltask.easyquizy.domain.repository.QuizRepository

class QuizRepositoryImpl : QuizRepository {

    override fun getQuizQuestions(): ArrayList<Question> =
        arrayListOf(
            Question(
                "question1",
                "correctAnswer",
                arrayListOf(
                    "incorrect1",
                    "incorrect2",
                    "incorrect3"
                )
            ),
            Question(
                "question2",
                "correctAnswer",
                arrayListOf(
                    "incorrect1",
                    "incorrect2",
                    "incorrect3"
                )
            ),
            Question(
                "question3",
                "correctAnswer",
                arrayListOf(
                    "incorrect1",
                    "incorrect2",
                    "incorrect3"
                )
            ),
            Question(
                "question4",
                "correctAnswer",
                arrayListOf(
                    "incorrect1",
                    "incorrect2",
                    "incorrect3"
                )
            ),
            Question(
                "question5",
                "correctAnswer",
                arrayListOf(
                    "incorrect1",
                    "incorrect2",
                    "incorrect3"
                )
            ),
            Question(
                "question6",
                "correctAnswer",
                arrayListOf(
                    "incorrect1",
                    "incorrect2",
                    "incorrect3"
                )
            )

        )

}