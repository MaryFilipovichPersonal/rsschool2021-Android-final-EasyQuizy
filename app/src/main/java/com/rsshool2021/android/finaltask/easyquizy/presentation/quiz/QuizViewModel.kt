package com.rsshool2021.android.finaltask.easyquizy.presentation.quiz

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rsshool2021.android.finaltask.easyquizy.domain.quiz.entity.QuestionDomain
import com.rsshool2021.android.finaltask.easyquizy.domain.quiz.usecase.GetQuizQuestionsUseCase
import com.rsshool2021.android.finaltask.easyquizy.presentation.common.mappers.QuestionDomainToQuizQuestionMapper
import com.rsshool2021.android.finaltask.easyquizy.presentation.quiz.entity.Question
import com.rsshool2021.android.finaltask.easyquizy.presentation.quiz.entity.Quiz
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuizViewModel @Inject constructor(private val getQuestionsUseCase: GetQuizQuestionsUseCase) :
    ViewModel() {

    private val _viewState = MutableStateFlow<QuizViewState>(QuizViewState.Success(Quiz()))
    val viewState: StateFlow<QuizViewState> get() = _viewState

    fun getQuiz() {
        viewModelScope.launch {
            _viewState.value = QuizViewState.Loading

            getQuestionsUseCase.execute()
                .onSuccess { result ->
                    _viewState.value = QuizViewState.Success(getMappedQuestions(result))
                }.onFailure { error ->
                    _viewState.value = QuizViewState.Error(error.localizedMessage.orEmpty())
                }
        }
    }

    private fun getMappedQuestions(result: List<QuestionDomain>): Quiz =
        Quiz(
            result.map {
                QuestionDomainToQuizQuestionMapper.map(it)
            }
        )
}