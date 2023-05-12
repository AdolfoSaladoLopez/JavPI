package com.adolfosalado.jav.utils

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import com.adolfosalado.jav.session.SessionData

class Preferences {
    companion object {
        fun recoverLevelOfUserInSharedPreferences(context: Context): String? {
            val preferences = context.getSharedPreferences(
                "sharedPreferences",
                AppCompatActivity.MODE_PRIVATE
            )

            if (preferences.all.isEmpty()) {
                val editor = preferences.edit()
                editor.putString("level", "1")
                editor.apply()
            }

            SessionData.levelUser = preferences.getString("level", "1").toString()
            return preferences.getString("level", "")
        }
    }
}