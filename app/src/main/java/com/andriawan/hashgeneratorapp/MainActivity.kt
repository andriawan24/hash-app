package com.andriawan.hashgeneratorapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.andriawan.hashgeneratorapp.databinding.ActivityMainBinding
import java.security.AlgorithmParameters
import java.security.MessageDigest

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}