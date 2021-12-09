package com.rsshool2021.android.finaltask.easyquizy.presentation.quiz.adapter

import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.view.children
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.radiobutton.MaterialRadioButton
import com.rsshool2021.android.finaltask.easyquizy.databinding.ViewHolderQuestionBinding
import com.rsshool2021.android.finaltask.easyquizy.presentation.common.extensions.fromHtml
import com.rsshool2021.android.finaltask.easyquizy.presentation.quiz.entity.Question

class QuestionViewHolder(
    private val binding: ViewHolderQuestionBinding,
    private val checkListener: (position: Int, checkedAnswer: String) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.vhqRgAnswers.setOnCheckedChangeListener { rg, id ->
            val radioButton = rg.children.firstOrNull { it.id == id } as? MaterialRadioButton
            radioButton?.let {
                checkListener(this.layoutPosition, it.text.toString())
            }
        }
    }

    fun bind(question: Question) {
        with(binding) {
            vhqRgAnswers.removeAllViews()
            vhqTvQuestion.text = question.question.fromHtml()

            question.allAnswers.forEach { answer ->
                val radioBtn = buildRadioButton(answer).apply {
                    isChecked = answer == question.checkedAnswer
                }
                vhqRgAnswers.addView(radioBtn)
            }
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