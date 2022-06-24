package com.andriawan.hashgeneratorapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.andriawan.hashgeneratorapp.data.HashType
import com.andriawan.hashgeneratorapp.utils.SingleEvent

class HomeViewModel: ViewModel() {

    private var _hashTypes = MutableLiveData<List<HashType>>()
    val hashTypes: LiveData<List<HashType>> = _hashTypes

    private var _updateDropdownList = MutableLiveData<SingleEvent<List<HashType>>>()
    val updateDropdownList: LiveData<SingleEvent<List<HashType>>> = _updateDropdownList

    fun getHashTypes() {
        _hashTypes.value = listOf(
            HashType(
                id = "1",
                name = "MD5"
            ),
            HashType(
                id = "2",
                name = "SHA-1"
            ),
            HashType(
                id = "3",
                name = "SHA-224"
            ),
            HashType(
                id = "4",
                name = "SHA-256"
            ),
            HashType(
                id = "5",
                name = "SHA-384"
            ),
            HashType(
                id = "6",
                name = "SHA-512"
            )
        )

        _updateDropdownList.value = SingleEvent(hashTypes.value)
    }
}