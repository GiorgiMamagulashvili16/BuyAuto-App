package com.example.buyauto_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.messaging.FirebaseMessaging

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val uid = FirebaseAuth.getInstance().currentUser?.uid!!
        FirebaseMessaging.getInstance().subscribeToTopic("/topics/$uid")
    }

    companion object {
        const val CONTENT_TYPE = "application/json"
        const val SERVER_KEY = "AAAAtx0Omf4:APA91bGjtH3brFC5aXxGFqvwn3AjYTHJJJiaJLA2ZXintNnXHm-aIkywJ4JzjamkL6Bt0KDHjfZZjMZ7PZd8PAEF9zC6Amk0L2kthhIMr2khneTHqpW3mkbCjwAjijVKTYJiM7cJ5WKj"
    }
}


/*
    ლოგინ/რეგისტრაცია, მანქანაების ჩამონათვალი, კატეგორიები, ვიპ კატეგორია,
     მანქანის დამატება, დეტალები, ნომრით დარეკვა, გუგლ მაპზე გადატანა, პუშ ნოთფიკაცია მანქანის დამატებაზე
 */