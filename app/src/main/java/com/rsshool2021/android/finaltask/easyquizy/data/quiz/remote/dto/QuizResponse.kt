package com.rsshool2021.android.finaltask.easyquizy.data.quiz.remote.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class QuizResponse(
    @SerialName("response_code")
    val responseCode: Int,
    @SerialName("results")
    val questionResponses: ArrayList<QuestionResponse>
)