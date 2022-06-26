package com.andriawan.hashgeneratorapp.utils

import com.andriawan.hashgeneratorapp.BuildConfig
import com.andriawan.hashgeneratorapp.R
import com.google.android.gms.tasks.Task
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings
import kotlinx.coroutines.tasks.await
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

object RemoteConfig {
    private val mFirebaseRemoteConfig = FirebaseRemoteConfig.getInstance()
    private const val FETCH_TIMEOUT = 900L // 15 minutes
    private const val DEBUG_MINIMUM_FETCH_INTERVAL = 0L // 0 Seconds
    private const val FETCH_INTERVAL = 3600L // 1 Hour

    // Options key
    private const val OPTIONS_KEY = "options"

    suspend fun fetchAndActivate() {
        val config = remoteConfigSettings {
            minimumFetchIntervalInSeconds = if (BuildConfig.DEBUG) DEBUG_MINIMUM_FETCH_INTERVAL else FETCH_INTERVAL
            fetchTimeoutInSeconds = FETCH_TIMEOUT
        }

        mFirebaseRemoteConfig.setConfigSettingsAsync(config).await()
        mFirebaseRemoteConfig.setDefaultsAsync(R.xml.remote_config_defaults)
        mFirebaseRemoteConfig.fetchAndActivate().await()
    }

    fun getOptions(): String {
        return mFirebaseRemoteConfig.getString(OPTIONS_KEY)
    }
}