package com.anmp_32bit.habittracker.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.anmp_32bit.habittracker.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}