package com.rsshool2021.android.finaltask.easyquizy.presentation.start

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.rsshool2021.android.finaltask.easyquizy.R
import com.rsshool2021.android.finaltask.easyquizy.databinding.FragmentStartScreenBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StartScreenFragment : Fragment(R.layout.fragment_start_screen) {

    private val binding by viewBinding(FragmentStartScreenBinding::bind)

    private val viewModel: StartScreenViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setClickListeners()

        setObservers()
    }

    private fun setClickListeners() {
        binding.fsBtnStartQuiz.setOnClickListener {
            navigateToQuiz()
        }
    }

    private fun navigateToQuiz() {
        findNavController().navigate(StartScreenFragmentDirections.actionGoToQuiz())
    }

    private fun setObservers() {
//        TODO("Not yet implemented")
    }

    companion object {

        fun newInstance() = StartScreenFragment()
    }
}