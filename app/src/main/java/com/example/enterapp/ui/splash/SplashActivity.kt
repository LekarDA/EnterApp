package com.example.enterapp.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.enterapp.R
import com.example.enterapp.ui.list.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        scheduleSplashScreen()
    }

    private fun scheduleSplashScreen() {
        val splashScreenDuration = getSplashScreenDuration()
        Handler(Looper.getMainLooper()).postDelayed({
            routeToNextPage()
            finish()
        }, splashScreenDuration)
    }

    private fun getSplashScreenDuration() = 1000L

    private fun routeToNextPage() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}