package com.andriawan.hashgeneratorapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andriawan.hashgeneratorapp.data.HashType
import com.andriawan.hashgeneratorapp.data.HashTypeResponse
import com.andriawan.hashgeneratorapp.utils.RemoteConfig
import com.andriawan.hashgeneratorapp.utils.SingleEvent
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.gson.Gson
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private var _hashTypes = MutableLiveData<List<HashType>>()
    val hashTypes: LiveData<List<HashType>> = _hashTypes

    private var _updateDropdownList = MutableLiveData<SingleEvent<List<HashType>>>()
    val updateDropdownList: LiveData<SingleEvent<List<HashType>>> = _updateDropdownList

    fun getHashTypes() {
        viewModelScope.launch {
            RemoteConfig.fetchAndActivate()
            val options = Gson().fromJson(
                RemoteConfig.getOptions(),
                HashTypeResponse::class.java
            )

            _hashTypes.value = options.data
            _updateDropdownList.value = SingleEvent(options.data)
        }
    }
}
