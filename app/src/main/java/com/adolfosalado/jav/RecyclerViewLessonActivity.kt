package com.adolfosalado.jav

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.adolfosalado.jav.adapter.LessonAdapter
import com.adolfosalado.jav.api.ApiService
import com.adolfosalado.jav.api.Retrofit.Companion.getRetrofit
import com.adolfosalado.jav.databinding.ActivityRecyclerViewLessonBinding
import com.adolfosalado.jav.models.Lesson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RecyclerViewLessonActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRecyclerViewLessonBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecyclerViewLessonBinding.inflate(layoutInflater)
        setContentView(binding.root)


        if (isInternetAvailable(this)) {
            initRecyclerView()
        } else {
            val intent = Intent(this, ConnectionActivity::class.java)
            startActivity(intent)
        }
    }

    private fun initRecyclerView() {
        binding.rvLesson.layoutManager = LinearLayoutManager(this)

        val apiService = getRetrofit().create(ApiService::class.java)

        lifecycleScope.launch {
            val call = apiService.getLessons()

            if (call.isSuccessful) {
                binding.rvLesson.adapter = LessonAdapter(call.body()!!) {
                    onItemSelected(it)
                }
            } else {
                Toast.makeText(
                    this@RecyclerViewLessonActivity,
                    "No es posible cargar datos",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    private fun onItemSelected(lesson: Lesson) {
        val intent = Intent(this, LessonActivity::class.java)
        intent.putExtra("id", lesson.id)
        startActivity(intent)
    }

    private fun isInternetAvailable(context: Context): Boolean {
        val connectivityManager: ConnectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val network = connectivityManager.activeNetwork ?: return false
        val capabilities = connectivityManager.getNetworkCapabilities(network) ?: return false

        return capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
                || capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
                || capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)
    }
}