package com.adolfosalado.jav

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.adolfosalado.jav.databinding.ActivityMainBinding
import com.adolfosalado.jav.utils.Preferences

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getCharacterInfo()

    }

    private fun getCharacterInfo() {
        binding.tvLevel.text = Preferences.recoverLevelOfUserInSharedPreferences(this)
        binding.btnLessons.setOnClickListener {
            val intent = Intent(this@MainActivity, RecyclerViewLessonActivity::class.java)
            startActivity(intent)
        }
    }
}