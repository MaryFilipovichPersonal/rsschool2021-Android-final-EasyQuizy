package com.rsshool2021.android.finaltask.easyquizy.presentation.quiz

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rsshool2021.android.finaltask.easyquizy.data.quiz.repository.QuizRepositoryImpl
import com.rsshool2021.android.finaltask.easyquizy.domain.quiz.entity.Question
import com.rsshool2021.android.finaltask.easyquizy.domain.quiz.usecase.GetQuizQuestionsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.ArrayList
import javax.inject.Inject

@HiltViewModel
class QuizViewModel @Inject constructor(private val getQuestionsUseCase: GetQuizQuestionsUseCase) : ViewModel() {

    private val _questions = MutableStateFlow<ArrayList<Question>>(arrayListOf())
    val questions: StateFlow<ArrayList<Question>> get() = _questions

    fun getQuiz() {
        viewModelScope.launch {
            getQuestionsUseCase.execute().collect { result ->
                _questions.value = result as ArrayList<Question>
            }
        }
    }

}