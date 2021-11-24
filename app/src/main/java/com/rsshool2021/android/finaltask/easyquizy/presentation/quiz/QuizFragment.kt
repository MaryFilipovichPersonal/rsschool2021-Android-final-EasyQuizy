package com.rsshool2021.android.finaltask.easyquizy.presentation.quiz

import android.os.Build
import android.os.Bundle
import android.text.Html
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.rsshool2021.android.finaltask.easyquizy.R
import com.rsshool2021.android.finaltask.easyquizy.databinding.FragmentQuizBinding
import com.rsshool2021.android.finaltask.easyquizy.domain.quiz.entity.Question
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.util.*

@AndroidEntryPoint
class QuizFragment : Fragment(R.layout.fragment_quiz) {

    private val binding by viewBinding(FragmentQuizBinding::bind)

    private val viewModel: QuizViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setClickListeners()

        setObservers()
    }

    private fun setClickListeners() {
        with(binding) {
            ftBtnGetQuestions.setOnClickListener {
                viewModel.getQuiz()
            }
        }
    }

    private fun setObservers() {
        viewModel.questions
            .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
            .onEach { handleResult(it) }
            .launchIn(lifecycleScope)
    }

    private fun handleResult(list: ArrayList<Question>) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            binding.ftTvData.text = Html.fromHtml(list.toString(), Html.FROM_HTML_MODE_LEGACY )
        }
    }

    companion object {

        fun newInstance() = QuizFragment()
    }
}