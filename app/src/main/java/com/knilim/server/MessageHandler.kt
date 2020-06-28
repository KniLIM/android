package com.knilim.server

import com.knilim.knilim.data.model.message.Message

interface MessageHandler {
    fun onMessageRcv(message: Message)

    fun onConnectAck()

    fun onSendAck()

    fun onConnectError()

    fun onSendError()
}