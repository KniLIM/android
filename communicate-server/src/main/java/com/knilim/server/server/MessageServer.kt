package com.knilim.server.server

import android.util.Log

object MessageServer {

    private const val TAG = "MessageServer"

    private lateinit var host : String
    private var port : Int? = null
    private lateinit var msgRcvHandler: MsgRcvHandler

    fun config(host : String, port : Int, msgRcvHandler: MsgRcvHandler) {
        MessageServer.host = host
        MessageServer.port = port
        MessageServer.msgRcvHandler = msgRcvHandler
    }

    fun connect(): Boolean {
        val url = "http://$host:$port/sockets"
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