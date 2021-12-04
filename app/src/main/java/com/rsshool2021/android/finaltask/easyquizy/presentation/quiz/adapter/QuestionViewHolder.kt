package com.rsshool2021.android.finaltask.easyquizy.presentation.quiz.adapter

import android.util.Log
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.view.children
import androidx.core.view.get
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.radiobutton.MaterialRadioButton
import com.rsshool2021.android.finaltask.easyquizy.databinding.ViewHolderQuestionBinding
import com.rsshool2021.android.finaltask.easyquizy.presentation.common.extensions.fromHtml
import com.rsshool2021.android.finaltask.easyquizy.presentation.quiz.entity.Question
import timber.log.Timber

const val TAG = "QuestionViewHolder"

class QuestionViewHolder(
    private val binding: ViewHolderQuestionBinding,
    private val checkListener: (position: Int, checkedAnswer: String) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.vhqRgAnswers.setOnCheckedChangeListener { rg, id ->
            val radioButton = (binding.vhqRgAnswers.children.firstOrNull { it.id == id } as MaterialRadioButton?)
            val checkedAnswer: String = if (radioButton?.isChecked == true) radioButton.text.toString() else "radio button null"
            Log.d(TAG,"setOnCheckedChangeListener(): checkedAnswer = $checkedAnswer")
            checkListener(this.layoutPosition, checkedAnswer)
        }
    }

    fun bind(question: Question) {
        with(binding) {
            vhqTvQuestion.text = question.question.fromHtml()
            vhqRgAnswers.removeAllViewsInLayout()
            vhqRgAnswers.removeAllViews()
            val answers = question.allAnswers.map { answer ->
                buildRadioButton(answer)
            }
            answers.forEach { radioBtn ->
                vhqRgAnswers.addView(radioBtn)
            }
            answers.find { it.text == question.checkedAnswer}?.isChecked = true
            Log.d(TAG,"bind(): question = $question")
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