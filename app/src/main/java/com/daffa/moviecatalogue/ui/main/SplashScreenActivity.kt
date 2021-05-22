package com.daffa.moviecatalogue.ui.main

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.daffa.moviecatalogue.R

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        supportActionBar?.hide()

        val icon: ImageView = findViewById(R.id.applogo)
        val slideInLeftAnim = AnimationUtils.loadAnimation(this, R.anim.slide_in_left)
        icon.startAnimation(slideInLeftAnim)

        val splashText: TextView = findViewById(R.id.tv_splash_text)
        val slideInLeftTextAnim = AnimationUtils.loadAnimation(this, R.anim.slide_in_left)
        splashText.startAnimation(slideInLeftTextAnim)

        Handler(mainLooper).postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, 1000)
    }
}