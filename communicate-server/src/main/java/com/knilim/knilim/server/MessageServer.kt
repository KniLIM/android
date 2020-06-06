package com.knilim.knilim.server

import android.util.Log
import java.util.*

object MessageServer {

    private const val TAG = "MessageServer"

    lateinit var host : String
    var port : Int? = null
    lateinit var msgRcvHandler: MsgRcvHandler

    fun config(host : String, port : Int, msgRcvHandler: MsgRcvHandler) {
        this.host = host
        this.port = port
        this.msgRcvHandler = msgRcvHandler
    }

    fun connect(): Boolean {
        val url = "http://${host}:${port}/sockets"
        return try {
            true
        } catch (e: Exception) {
            Log.e(TAG, e.message!!)
            false
        }
    }

    fun sendMessage(): Boolean {
        return try {
            true
        } catch (e: Exception) {
            Log.e(TAG, e.message!!)
            false
        }
    }
}