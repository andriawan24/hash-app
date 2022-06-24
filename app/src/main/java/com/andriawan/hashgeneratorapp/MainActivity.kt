package com.andriawan.hashgeneratorapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import java.security.AlgorithmParameters
import java.security.MessageDigest

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val digest = MessageDigest.getInstance("MD5")
    }
}