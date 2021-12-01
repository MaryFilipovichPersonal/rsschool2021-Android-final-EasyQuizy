package com.rsshool2021.android.finaltask.easyquizy.presentation.quiz.adapter

import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.radiobutton.MaterialRadioButton
import com.rsshool2021.android.finaltask.easyquizy.databinding.ViewHolderQuestionBinding
import com.rsshool2021.android.finaltask.easyquizy.domain.quiz.entity.QuestionDomain
import com.rsshool2021.android.finaltask.easyquizy.presentation.common.extensions.fromHtml
import com.rsshool2021.android.finaltask.easyquizy.presentation.quiz.entity.Question

class QuestionViewHolder(
    private val binding: ViewHolderQuestionBinding
) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.vhqRgAnswers.setOnCheckedChangeListener { _, id ->

        }
    }

    fun bind(question: Question) {
        with(binding) {
            vhqTvQuestion.text = question.question.fromHtml()
            vhqRgAnswers.removeAllViewsInLayout()
            val answers = question.allAnswers.map { answer -> buildRadioButton(answer.fromHtml()) }
            answers.forEach { radioBtn -> vhqRgAnswers.addView(radioBtn) }
        }
    }

    private fun buildRadioButton(text: String) = MaterialRadioButton(this.itemView.context).apply {
        layoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        this.text = text
    }

}