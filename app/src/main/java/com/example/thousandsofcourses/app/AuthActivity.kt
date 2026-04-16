package com.example.thousandsofcourses.app

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.thousandsofcourses.databinding.ActivityAuthBinding

class AuthActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val binding: ActivityAuthBinding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}