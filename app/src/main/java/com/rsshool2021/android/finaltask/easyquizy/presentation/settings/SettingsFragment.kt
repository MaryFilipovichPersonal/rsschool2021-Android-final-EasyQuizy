package com.rsshool2021.android.finaltask.easyquizy.presentation.settings

import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.rsshool2021.android.finaltask.easyquizy.R

class SettingsFragment : PreferenceFragmentCompat() {

    private val preferenceChangeListener = Preference.OnPreferenceChangeListener { _, newValue ->
        when (newValue) {
            getString(R.string.key_dark) -> {
                updateTheme(AppCompatDelegate.MODE_NIGHT_YES)
            }
            getString(R.string.key_light) -> {
                updateTheme(AppCompatDelegate.MODE_NIGHT_NO)
            }
            else -> {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY)
                }
            }
        }
        true
    }

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.fragment_settings, rootKey)

        setChangeListener()

    }

    private fun setChangeListener() {
        val preferences: Preference? = findPreference(resources.getString(R.string.key_theme))
        preferences?.onPreferenceChangeListener = preferenceChangeListener
    }

    private fun updateTheme(nightMode: Int): Boolean {
        AppCompatDelegate.setDefaultNightMode(nightMode)
        return true
    }
}