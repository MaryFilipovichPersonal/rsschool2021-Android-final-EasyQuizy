package com.rsshool2021.android.finaltask.easyquizy.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import by.kirich1409.viewbindingdelegate.viewBinding
import com.rsshool2021.android.finaltask.easyquizy.Constants
import com.rsshool2021.android.finaltask.easyquizy.R
import com.rsshool2021.android.finaltask.easyquizy.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val viewBinding by viewBinding(ActivityMainBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding.root

        setTestFragment()
    }

    private fun setTestFragment() {
        supportFragmentManager.beginTransaction()
            .replace(
                viewBinding.amContainer.id,
                TestFragment.newInstance(),
                Constants.TEST_FRAGMENT_TAG
            )
            .commit()
    }
}