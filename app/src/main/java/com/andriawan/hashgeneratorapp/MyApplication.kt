package com.andriawan.hashgeneratorapp

import android.app.Application
import android.util.Log
import com.google.firebase.ktx.Firebase
import com.google.firebase.ktx.app
import com.google.firebase.ktx.initialize
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings

class MyApplication : Application() {

    override fun onCreate() {
        Firebase.initialize(this)
        super.onCreate()
    }
}