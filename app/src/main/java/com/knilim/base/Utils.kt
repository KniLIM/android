package com.knilim.base

import com.google.gson.GsonBuilder
import com.knilim.knilim.data.database.ImDatabase
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull

object Utils {

    val JSON: MediaType? = "application/json; charset=utf-8".toMediaTypeOrNull()

    val gson = GsonBuilder().create();

    fun getUrl(extra: String): String {
        return "http://im.loheagn.com:8080/$extra"
    }

    lateinit var db : ImDatabase
}