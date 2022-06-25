package com.andriawan.hashgeneratorapp.ui.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.andriawan.hashgeneratorapp.utils.SingleEvent
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

class DetailViewModel : ViewModel() {

    private var _showResult = MutableLiveData<SingleEvent<String>>()
    val showResult: LiveData<SingleEvent<String>> = _showResult

    fun processPlainText(plainText: String, algorithm: String) {
        try {
            try {
                val bytes = MessageDigest.getInstance(algorithm).digest(plainText.toByteArray())
                val result = bytes.joinToString("") { "%02x".format(it) }
                Log.i(DetailViewModel::class.simpleName, "result is: $result")
                _showResult.value = SingleEvent(result)
            } catch (e: NoSuchAlgorithmException) {
                Log.e(DetailViewModel::class.simpleName, "processPlainText: ${e.localizedMessage}")
            }
        } catch (e: Exception) {
            Log.e(DetailViewModel::class.simpleName, "processPlainText: ${e.localizedMessage}")
        }
    }
}