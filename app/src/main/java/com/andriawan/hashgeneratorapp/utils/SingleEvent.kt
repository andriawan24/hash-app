package com.andriawan.hashgeneratorapp.utils

class SingleEvent<T>(
    private val content: T? = null
) {
    private var isHandled = false

    fun hasBeenHandled(): T? {
        if (isHandled) {
            return null
        }

        isHandled = true
        return content
    }
}