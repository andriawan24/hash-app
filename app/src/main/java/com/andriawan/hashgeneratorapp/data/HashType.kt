package com.andriawan.hashgeneratorapp.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class HashType(
    val id: String,
    val name: String
) : Parcelable

data class HashTypeResponse(
    @SerializedName("data")
    val data: List<HashType>
)