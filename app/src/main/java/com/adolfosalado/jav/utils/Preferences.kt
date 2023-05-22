package com.adolfosalado.jav.utils

import android.content.Context
import androidx.appcompat.app.AppCompatActivity

class Preferences {
    companion object {
        fun recoverLevelOfUserInSharedPreferences(context: Context): String? {
            val preferences = context.getSharedPreferences(
                "sharedPreferences",
                AppCompatActivity.MODE_PRIVATE
            )

            if (!preferences.contains("level")) {
                val editor = preferences.edit()
                editor.putString("level", "1")
                editor.apply()
            }

            return preferences.getString("level", "")
        }

        fun recoverIfIsTheFirstTime(context: Context): String? {
            val preferences = context.getSharedPreferences(
                "sharedPreferences",
                AppCompatActivity.MODE_PRIVATE
            )

            if (!preferences.contains("isFirstTime")) {
                val editor = preferences.edit()
                editor.putString("isFirstTime", "true")
                editor.apply()
            }

            return preferences.getString("isFirstTime", "")
        }
    }
}