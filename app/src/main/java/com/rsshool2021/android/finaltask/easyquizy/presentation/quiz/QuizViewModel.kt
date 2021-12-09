package com.rsshool2021.android.finaltask.easyquizy.presentation.quiz

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rsshool2021.android.finaltask.easyquizy.domain.quiz.entity.QuestionDomain
import com.rsshool2021.android.finaltask.easyquizy.domain.quiz.usecase.GetQuizQuestionsUseCase
import com.rsshool2021.android.finaltask.easyquizy.presentation.common.mappers.QuestionDomainToQuizQuestionMapper
import com.rsshool2021.android.finaltask.easyquizy.presentation.quiz.entity.Quiz
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuizViewModel @Inject constructor(private val getQuestionsUseCase: GetQuizQuestionsUseCase) :
    ViewModel() {

    private val _viewState = MutableStateFlow<QuizViewState>(QuizViewState.Loading)
    val viewState: StateFlow<QuizViewState> get() = _viewState

    private val successfulViewState get() = _viewState.value as? QuizViewState.Success

    fun getQuiz() {
        if (viewState.value !is QuizViewState.Success) {
            viewModelScope.launch {
                _viewState.value = QuizViewState.Loading

                getQuestionsUseCase.execute()
                    .onSuccess { result ->
                        val quiz = getMappedQuestions(result)
                        _viewState.value = QuizViewState.Success(quiz)
                    }.onFailure { error ->
                        _viewState.value = QuizViewState.Error(error.localizedMessage.orEmpty())
                    }
            }
        }
    }

    fun updatePosition(position: Int){
        successfulViewState?.let {
            val progress = position + 1
            val isSubmitBtnVisible = it.quiz.questions.size == progress
            _viewState.value = it.copy(
                currentPosition = position,
                progress = progress,
                isSubmitBtnVisible = isSubmitBtnVisible)
        }
    }

    fun setCheckedAnswer(position: Int, answer: String) {
        successfulViewState?.let {
            val questions = it.quiz.questions.toMutableList()
            if(questions[position].checkedAnswer != answer) {
                questions[position] = questions[position].copy(checkedAnswer = answer)
                _viewState.value = it.copy(quiz = Quiz(questions))
            }
        }
    }

    fun getQuizResult(): Int{
        return successfulViewState?.quiz?.calculateScore() ?: 0
    }


    private fun getMappedQuestions(result: List<QuestionDomain>): Quiz =
        Quiz(
            result.map {
                QuestionDomainToQuizQuestionMapper.map(it)
            }
        )
}