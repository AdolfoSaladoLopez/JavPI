package com.adolfosalado.jav

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.adolfosalado.jav.utils.Preferences

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val splashScreenDuration = 2000L
        Thread {
            Thread.sleep(splashScreenDuration)
            checkIfIsTheFirstTime()
            finish()
        }.start()
    }

    private fun checkIfIsTheFirstTime() {
        val trueOrFalse = Preferences.recoverIfIsTheFirstTime(this)

        if (trueOrFalse == "true") {
            val preferences = this.getSharedPreferences(
                "sharedPreferences",
                MODE_PRIVATE
            )

            val editor = preferences.edit()
            editor.putString("isFirstTime", "false")
            editor.apply()

            val intent = Intent(this, InicioActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)

        } else if (trueOrFalse == "false") {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)

        }
    }
}