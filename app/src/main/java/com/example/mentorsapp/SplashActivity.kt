package com.example.mentorsapp

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_splash)
        val imageView = findViewById<ImageView>(R.id.imageView)
        val imageView2 = findViewById<ImageView>(R.id.imageView2)
        val textView = findViewById<TextView>(R.id.textView)

        textView.alpha = 0f
        textView.animate().setDuration(3000).alpha(1f).withEndAction {
            val i = Intent(this, Loginpage::class.java)
            startActivity(i)
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            finish()
        }
        imageView.alpha = 0f
        imageView.animate().setDuration(3000).alpha(1f).withEndAction {
            val i = Intent(this, Loginpage::class.java)
            startActivity(i)
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            finish()
        }
        imageView2.alpha = 0f
        imageView2.animate().setDuration(3000).alpha(1f).withEndAction {
            val i = Intent(this, Loginpage::class.java)
            startActivity(i)
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            finish()
        }


//        supportActionBar?.hide()
//        android.os.Handler().postDelayed(Runnable {
//            val iHome = Intent(this, loginpage::class.java)
//            startActivity(iHome)
//            finish()
//        }, 3000)
    }
}