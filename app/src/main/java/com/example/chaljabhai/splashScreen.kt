package com.example.chaljabhai

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import post_folder.postActivity

class splashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)


    Handler().postDelayed({
        val intent= Intent(this,MainActivity::class.java)
        startActivity(intent)
        finish()

    },2000)

    }
}