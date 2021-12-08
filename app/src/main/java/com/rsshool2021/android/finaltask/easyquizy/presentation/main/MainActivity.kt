package com.rsshool2021.android.finaltask.easyquizy.presentation.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.rsshool2021.android.finaltask.easyquizy.utils.Constants
import com.rsshool2021.android.finaltask.easyquizy.R
import com.rsshool2021.android.finaltask.easyquizy.databinding.ActivityMainBinding
import com.rsshool2021.android.finaltask.easyquizy.presentation.quiz.QuizFragment
import com.rsshool2021.android.finaltask.easyquizy.presentation.start.StartScreenFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val viewBinding by viewBinding(ActivityMainBinding::bind)

    private val navHostFragment by lazy {
        supportFragmentManager.findFragmentById(R.id.am_container) as  NavHostFragment
    }

    private val navController by lazy {
        navHostFragment.navController
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding.root

    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

}