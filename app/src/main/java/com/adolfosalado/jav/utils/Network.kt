package com.adolfosalado.jav.utils

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.widget.Toast
import com.adolfosalado.jav.ConnectionActivity
import com.adolfosalado.jav.MainActivity

class Network(
    private val context: Context,
    private val onInternetRestored: () -> Unit,
    private val onInternetLost: () -> Unit
) : ConnectivityManager.NetworkCallback() {

    override fun onAvailable(network: android.net.Network) {
        super.onAvailable(network)

        onInternetRestored()

        val intent = Intent(context, MainActivity::class.java)
        context.startActivity(intent)
    }

    override fun onLost(network: android.net.Network) {
        super.onLost(network)

        Toast.makeText(context, "No hay conexión", Toast.LENGTH_LONG).show()

        val intent = Intent(context, ConnectionActivity::class.java)
        context.startActivity(intent)

        onInternetLost()
    }
}