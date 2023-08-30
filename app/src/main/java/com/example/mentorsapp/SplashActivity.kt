package com.example.mentorsapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import java.util.logging.Handler

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_splash);
        supportActionBar?.hide()
        android.os.Handler().postDelayed(Runnable {
            val iHome = Intent(this@SplashActivity, loginpage::class.java)
            startActivity(iHome)
            finish()
        }, 1500)
    }
}