package com.rsshool2021.android.finaltask.easyquizy.presentation.quiz

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import by.kirich1409.viewbindingdelegate.viewBinding
import com.rsshool2021.android.finaltask.easyquizy.R
import com.rsshool2021.android.finaltask.easyquizy.databinding.FragmentQuizBinding
import com.rsshool2021.android.finaltask.easyquizy.presentation.quiz.adapter.QuizViewPagerAdapter
import com.rsshool2021.android.finaltask.easyquizy.presentation.quiz.entity.Quiz
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class QuizFragment : Fragment(R.layout.fragment_quiz) {

    private val binding by viewBinding(FragmentQuizBinding::bind)

    private val viewModel: QuizViewModel by viewModels()

    private val adapter: QuizViewPagerAdapter by lazy {
        QuizViewPagerAdapter { position, checkedAnswer ->
            handleAnswerCheck(position, checkedAnswer)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setViewPager()

        setObservers()

        setListeners()

        viewModel.getQuiz()
    }

    private fun setViewPager() {
        binding.fqViewPager.adapter = adapter
    }

    private fun setListeners() {
        with(binding) {
            fqBtnRetry.setOnClickListener {
                viewModel.getQuiz()
            }
            fqViewPager.registerOnPageChangeCallback(object :
                ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    viewModel.updatePosition(position)
                }
            })
            fqBtnSubmitQuiz.setOnClickListener {
                val result = viewModel.getQuizResult()
                findNavController().navigate(
                    QuizFragmentDirections.actionQuizDestToResultDest(
                        result
                    )
                )
            }
        }
    }

    private fun setObservers() {
        viewModel.viewState
            .flowWithLifecycle(viewLifecycleOwner.lifecycle, Lifecycle.State.STARTED)
            .onEach {
                when (it) {
                    is QuizViewState.Loading -> {
                        handleLoadingState()
                    }
                    is QuizViewState.Success -> {
                        handleSuccessResult(it)
                    }
                    is QuizViewState.Error -> {
                        handleFailResult(it.errorMessage)
                    }
                }
            }
            .launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun handleSuccessResult(viewState: QuizViewState.Success) {
        with(viewState) {
            updateQuiz(quiz)
            setProgress(progress)
            setPosition(currentPosition)
            setSubmitBtnVisibility(isSubmitBtnVisible)
        }
        showLoadingUi(false)
        showErrorUi(false)
        showQuizUi(true)
    }

    private fun handleFailResult(error: String) {
        showLoadingUi(false)
        showQuizUi(false)
        showErrorUi(true, error)
    }

    private fun handleLoadingState() {
        showErrorUi(false)
        showQuizUi(false)
        showLoadingUi(true)
    }

    private fun handleAnswerCheck(position: Int, checkedAnswer: String) {
        viewModel.setCheckedAnswer(position, checkedAnswer)
    }

    private fun showLoadingUi(isVisible: Boolean) {
        binding.fqPbLoadingProgress.isVisible = isVisible
    }

    private fun showQuizUi(isVisible: Boolean) {
        with(binding) {
            fqPbQuizProgress.isVisible = isVisible
            fqViewPager.isVisible = isVisible
        }
    }

    private fun showErrorUi(isVisible: Boolean, error: String = "") {
        with(binding) {
            fqTvErrorMessage.text = error
            fqBtnRetry.isVisible = isVisible
            fqTvErrorMessage.isVisible = isVisible
        }
    }

    private fun updateQuiz(quiz: Quiz) {
        adapter.submitList(quiz.questions)
    }

    private fun setPosition(position: Int) {
        if (binding.fqViewPager.currentItem != position)
            binding.fqViewPager.setCurrentItem(position, false)
    }

    private fun setProgress(progress: Int) {
        binding.fqPbQuizProgress.progress = progress
    }

    private fun setSubmitBtnVisibility(isVisible: Boolean) {
        binding.fqBtnSubmitQuiz.visibility = if (isVisible) {
            View.VISIBLE
        } else {
            View.INVISIBLE
        }
    }
}