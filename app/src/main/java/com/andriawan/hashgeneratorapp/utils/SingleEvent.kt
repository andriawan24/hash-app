package com.andriawan.hashgeneratorapp.utils

class SingleEvent<T>(
    private val content: T? = null
) {
    private var isHandled = false

    fun hasBeenHandled(): T? {
        return if (isHandled) {
            null
        } else {
            isHandled = true
            content
        }
    }
}