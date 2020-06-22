package com.knilim.server

import android.util.Log

object MessageServer {

    private const val TAG = "MessageServer"

    private lateinit var host : String
    private var port : Int? = null
    private lateinit var msgRcvHandler: MsgRcvHandler

    private var isConnected: Boolean = false

    fun config(host : String, port : Int, msgRcvHandler: MsgRcvHandler) {
        MessageServer.host = host
        MessageServer.port = port
        MessageServer.msgRcvHandler = msgRcvHandler
    }

    fun connect() {
        val url = "http://$host:$port/sockets"
        try {
            // TODO 初始化,并连接服务器
            isConnected = true
        } catch (e: Exception) {
            Log.e(TAG, e.message!!)
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