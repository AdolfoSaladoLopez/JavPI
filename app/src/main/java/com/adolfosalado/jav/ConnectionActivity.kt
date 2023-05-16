package com.adolfosalado.jav

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.adolfosalado.jav.databinding.ActivityConnectionBinding
import com.adolfosalado.jav.utils.Network

class ConnectionActivity : AppCompatActivity() {
    private lateinit var network: Network
    private lateinit var binding: ActivityConnectionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConnectionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        checkConnection()
    }

    private fun checkConnection() {
        network = Network(
            this,
            { // Función de devolución de llamada cuando se restablece la conexión a Internet
                runOnUiThread {
                    // Realiza las acciones necesarias aquí, como regresar a otra actividad
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish() // Opcionalmente, finaliza la actividad intermedia si ya no la necesitas
                }
            },
            {
                val intent = Intent(this, ConnectionActivity::class.java)
                startActivity(intent)
                finish() // Opcionalmente, finaliza la actividad intermedia si ya no la necesitas
            }
        )

        val connectivityManager =
            getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkRequest = NetworkRequest.Builder()
            .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            .build()
        connectivityManager.registerNetworkCallback(networkRequest, network)
    }
}