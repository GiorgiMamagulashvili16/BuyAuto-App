package com.example.buyauto_app

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.buyauto_app.services.BatteryReceiver
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.messaging.FirebaseMessaging

class MainActivity : AppCompatActivity() {

    private val batteryReceiver by lazy { BatteryReceiver() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val uid =  FirebaseAuth.getInstance().currentUser?.uid
        uid?.let {
            FirebaseMessaging.getInstance().subscribeToTopic("/topics/$uid")
        }

        registerReceiver(batteryReceiver, IntentFilter(Intent.ACTION_BATTERY_CHANGED))
    }

    override fun onStop() {
        super.onStop()
        unregisterReceiver(batteryReceiver)
    }

    companion object {
        const val CONTENT_TYPE = "application/json"
        const val SERVER_KEY = "AAAAw3ODhoI:APA91bEQ7SDFlgPWqztfzGDyh3OjIy5AJV8At-YRHRfuxsiJvixbubV-eA_6kcE_0pt4AetSrOoatcRyUmJi0IzrMZjTCuIVXNWgGsq2hkPX9nU6T1wXGkGfASI37pBkQnyKs2mJWaAX"
    }
}