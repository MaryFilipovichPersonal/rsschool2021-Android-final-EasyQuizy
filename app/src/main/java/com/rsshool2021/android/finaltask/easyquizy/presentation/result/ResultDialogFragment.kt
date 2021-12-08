package com.rsshool2021.android.finaltask.easyquizy.presentation.result

import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.rsshool2021.android.finaltask.easyquizy.R
import com.rsshool2021.android.finaltask.easyquizy.databinding.DialogQuizResultBinding

class ResultDialogFragment : DialogFragment(R.layout.dialog_quiz_result) {

    private val binding by viewBinding(DialogQuizResultBinding::bind)

    private val arguments by navArgs<ResultDialogFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.dqrTvResult.text = resources.getString(R.string.quiz_result, arguments.result)

        setListeners()


    }

    private fun setListeners() {
        binding.dqrBtnPositive.setOnClickListener {
            val navController = findNavController()
            navController.navigate(ResultDialogFragmentDirections.actionResultDestToStartScreenDest())
        }
        dialog?.setOnCancelListener {
            val navController = findNavController()
            navController.navigate(ResultDialogFragmentDirections.actionResultDestToStartScreenDest())
        }
    }

}