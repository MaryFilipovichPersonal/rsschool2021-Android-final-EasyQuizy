package com.rsshool2021.android.finaltask.easyquizy.domain.repository

import com.rsshool2021.android.finaltask.easyquizy.domain.models.Question

interface QuizRepository {

    fun getQuizQuestions(): ArrayList<Question>
}
