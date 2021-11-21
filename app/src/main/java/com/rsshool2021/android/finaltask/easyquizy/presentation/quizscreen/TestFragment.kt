package com.rsshool2021.android.finaltask.easyquizy.presentation.quizscreen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.rsshool2021.android.finaltask.easyquizy.R
import com.rsshool2021.android.finaltask.easyquizy.data.repository.QuizRepositoryImpl
import com.rsshool2021.android.finaltask.easyquizy.databinding.FragmentTestBinding
import com.rsshool2021.android.finaltask.easyquizy.domain.usecase.GetQuizQuestionsUseCase

class TestFragment : Fragment(R.layout.fragment_test) {

    private val viewBinding by viewBinding(FragmentTestBinding::bind)

    private val getQuizQuestionsUseCase = GetQuizQuestionsUseCase(QuizRepositoryImpl())

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setClickListeners()
    }

    private fun setClickListeners() {
        with(viewBinding) {
            ftBtnGetQuestions.setOnClickListener {
                viewBinding.ftTvData.text = getQuizQuestionsUseCase.execute().toString()
            }
        }
    }

    companion object {

        fun newInstance() = TestFragment()
    }
}