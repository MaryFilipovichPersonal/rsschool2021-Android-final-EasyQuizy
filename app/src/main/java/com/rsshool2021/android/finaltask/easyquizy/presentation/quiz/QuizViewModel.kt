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

    private val _quiz = MutableStateFlow(Quiz())
    val quiz: StateFlow<Quiz> get() = _quiz

    fun getQuiz() {
        viewModelScope.launch {
            _viewState.value = QuizViewState.Loading

            getQuestionsUseCase.execute()
                .onSuccess { result ->
                    val quiz = getMappedQuestions(result)
                    _viewState.value = QuizViewState.Success(quiz)
                    _quiz.value = quiz
                }.onFailure { error ->
                    _viewState.value = QuizViewState.Error(error.localizedMessage.orEmpty())
                }
        }
    }

    fun setCheckedAnswer(position: Int, answer: String) {
        val questions: MutableList<Question> = _quiz.value.questions.toMutableList()
        val question = questions[position].copy()
        if (question.checkedAnswer != answer) {
            questions[position] = question.copy(checkedAnswer = answer)
            _quiz.value = Quiz(questions)
        }
    }

    fun getQuizResult(): Int = _quiz.value.calculateScore()

    private fun getMappedQuestions(result: List<QuestionDomain>): Quiz =
        Quiz(
            result.map {
                QuestionDomainToQuizQuestionMapper.map(it)
            }
        )
}