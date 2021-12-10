package com.rsshool2021.android.finaltask.easyquizy.presentation.quiz.adapter

import androidx.recyclerview.widget.RecyclerView
import com.rsshool2021.android.finaltask.easyquizy.R
import com.rsshool2021.android.finaltask.easyquizy.databinding.ViewHolderSubmitBinding

class SubmitViewHolder(
    private val binding: ViewHolderSubmitBinding,
    private val submitListener: () -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.fqBtnSubmitQuiz.setOnClickListener {
            submitListener()
        }
    }

    fun bind(checkedProgress: Int) {
        with(binding) {
            vhqTvCheckedProgress.text =
                root.context.getString(R.string.quiz_you_answered, checkedProgress)
        }
    }
}