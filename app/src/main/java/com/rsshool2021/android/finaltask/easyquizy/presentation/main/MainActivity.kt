package com.rsshool2021.android.finaltask.easyquizy.presentation.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupActionBarWithNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.rsshool2021.android.finaltask.easyquizy.R
import com.rsshool2021.android.finaltask.easyquizy.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val binding by viewBinding(ActivityMainBinding::bind)

    private val viewModel: MainViewModel by viewModels()

    private val destinationChangeListener =
        NavController.OnDestinationChangedListener { controller, destination, arguments ->
            binding.amToolbar.menu.findItem(R.id.settings_dest)?.isVisible =
                when (destination.id) {
                    R.id.settings_dest, R.id.quiz_dest, R.id.result_dest -> false
                    else -> true
                }
        }

    private val navHostFragment by lazy {
        supportFragmentManager.findFragmentById(R.id.am_container) as NavHostFragment
    }

    private val navController by lazy {
        navHostFragment.navController.apply {
            addOnDestinationChangedListener(destinationChangeListener)
        }
    }

    private val appBarConfiguration: AppBarConfiguration by lazy {
        AppBarConfiguration(navController.graph)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.root

        setupToolbar()

        setObservers()

        viewModel.getAppTheme()

    }

    private fun setupToolbar() {
        setSupportActionBar(binding.amToolbar)
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return item.onNavDestinationSelected(navController) ||
                super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        navController.removeOnDestinationChangedListener(destinationChangeListener)
        super.onDestroy()
    }

    private fun setObservers() {
        viewModel.appTheme
            .flowWithLifecycle(lifecycle, Lifecycle.State.CREATED)
            .onEach {
                setupTheme(it)
            }
            .launchIn(lifecycleScope)
    }

    private fun setupTheme(theme: Int) {
        AppCompatDelegate.setDefaultNightMode(theme)
    }

}