package com.example.common.network

import com.example.common.BuildConfig
import java.math.BigInteger
import java.security.MessageDigest
import java.sql.Timestamp

object Hash {
    val timestamp = Timestamp(System.currentTimeMillis()).time.toString()
    const val PUBLIC_KEY = BuildConfig.API_KEY
    const val PRIVATE_KEY = BuildConfig.PRIVATE_API_KEY

    fun getHash(): String {
        val input = timestamp + PRIVATE_KEY + PUBLIC_KEY
        val md = MessageDigest.getInstance("MD5")
        return BigInteger(1, md.digest(input.toByteArray())).toString(16).padStart(32, '0')
    }
}