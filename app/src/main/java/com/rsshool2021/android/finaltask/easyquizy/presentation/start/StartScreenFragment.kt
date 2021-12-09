package com.rsshool2021.android.finaltask.easyquizy.presentation.start

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.rsshool2021.android.finaltask.easyquizy.R
import com.rsshool2021.android.finaltask.easyquizy.databinding.FragmentStartScreenBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class StartScreenFragment : Fragment(R.layout.fragment_start_screen) {

    private val binding by viewBinding(FragmentStartScreenBinding::bind)

    private val viewModel: StartScreenViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setObservers()

        setClickListeners()

        viewModel.getBestResult()
    }

    private fun setObservers() {
        viewModel.bestResult
            .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
            .onEach {
                setupBestResult(it)
            }
            .launchIn(lifecycleScope)
    }

    private fun setClickListeners() {
        with(binding) {
            fsBtnStartQuiz.setOnClickListener {
                navigateToQuiz()
            }
            fsBtnSettings.setOnClickListener {
                navigateToSettings()
            }
        }
    }

    private fun setupBestResult(result: Int) {
        with(binding) {
            if (result == -1) fsTvBestResult.isVisible = false
            else {
                fsTvBestResult.text = resources.getString(R.string.quiz_best_result, result)
                fsTvBestResult.isVisible = true
            }
        }
    }

    private fun navigateToQuiz() {
        findNavController().navigate(StartScreenFragmentDirections.actionGoToQuiz())
    }

    private fun navigateToSettings() {
        findNavController().navigate(StartScreenFragmentDirections.actionGoToSettings())
    }

}