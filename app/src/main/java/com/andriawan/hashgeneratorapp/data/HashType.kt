package com.andriawan.hashgeneratorapp.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class HashType(
    val id: String,
    val name: String
) : Parcelable
