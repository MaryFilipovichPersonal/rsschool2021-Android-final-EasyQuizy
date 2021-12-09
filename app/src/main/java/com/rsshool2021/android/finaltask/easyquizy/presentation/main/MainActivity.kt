package com.rsshool2021.android.finaltask.easyquizy.presentation.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.rsshool2021.android.finaltask.easyquizy.utils.Constants
import com.rsshool2021.android.finaltask.easyquizy.R
import com.rsshool2021.android.finaltask.easyquizy.databinding.ActivityMainBinding
import com.rsshool2021.android.finaltask.easyquizy.presentation.quiz.QuizFragment
import com.rsshool2021.android.finaltask.easyquizy.presentation.start.StartScreenFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val viewBinding by viewBinding(ActivityMainBinding::bind)

    private val viewModel: MainViewModel by viewModels()

    private val navHostFragment by lazy {
        supportFragmentManager.findFragmentById(R.id.am_container) as  NavHostFragment
    }

    private val navController by lazy {
        navHostFragment.navController
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding.root

        setObservers()

        viewModel.getAppTheme()

    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    private fun setObservers() {
        viewModel.appTheme
            .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
            .onEach {
                setupTheme(it)
            }
            .launchIn(lifecycleScope)
    }

    private fun setupTheme(theme: Int) {
        AppCompatDelegate.setDefaultNightMode(theme)
    }

}