package com.knilim.server

import android.util.Log
import com.github.nkzawa.emitter.Emitter
import com.github.nkzawa.socketio.client.Ack
import com.github.nkzawa.socketio.client.IO
import com.github.nkzawa.socketio.client.Socket
import com.knilim.knilim.data.model.message.Message
import com.knilim.server.serialize.MessageProto
import com.knilim.server.serialize.RedundanceProto

object MessageServer {

    private const val TAG = "MessageServer"

    private lateinit var socket: Socket

    private lateinit var messageHandler: MessageHandler

    private var isConnected: Boolean = false

    fun config(
        host: String,
        port: Int,
        token: String,
        userId: String,
        device: String,
        messageHandler: MessageHandler
    ) {
        this.messageHandler = messageHandler
        val url = "http://${host}:${port}/sockets?token=$token&userId=$userId&device=$device"
        socket = IO.socket(url)
    }

    fun connect() {
        try {
            if (!isConnected) {
                socket.on("connect-error") {
                    isConnected = false
                    messageHandler.onConnectError()
                }
                socket.on("connect-ack") {
                    isConnected = true
                    messageHandler.onConnectAck()
                }
                socket.on("rcv-msg") {
                    Log.d(TAG, "rcv-msg")
                    val data = it[0] as ByteArray
                    val messageProto = MessageProto.Msg.parseFrom(data)
                    val message = Message(
                        messageProto.msgId,
                        messageProto.msgType.ordinal + 1,
                        messageProto.contentType.ordinal + 1,
                        messageProto.sender,
                        messageProto.receiver,
                        messageProto.createAt,
                        messageProto.content,
                        messageProto.sender
                    )
                    Log.d(TAG, message.toString())
                    messageHandler.onMessageRcv(message)
                }
                socket.connect()
            }
        } catch (e: Exception) {
            Log.e(TAG, e.message!!)
        }
    }

    fun disConnect() {
        socket.disconnect()
    }

    fun sendMessage(message: Message) {
        try {
            // 第一次序列化
            val messageProto = MessageProto.Msg.newBuilder()
                .setMsgId(message.id)
                .setMsgType(MessageProto.Msg.MsgType.valueOf(message.messageType))
                .setContentType(MessageProto.Msg.ContentType.valueOf(message.contentType))
                .setSender(message.sender)
                .setReceiver(message.receiver)
                .setCreateAt(message.createdTime)
                .setContent(message.content)
                .build().toByteString()

            val messageProto2 = MessageProto.Msg.parseFrom(messageProto)
            val message2 = Message(
                messageProto2.msgId,
                messageProto2.msgType.ordinal + 1,
                messageProto2.contentType.ordinal + 1,
                messageProto2.sender,
                messageProto2.receiver,
                messageProto2.createAt,
                messageProto2.content,
                messageProto2.sender
            )
            Log.d(TAG, message2.toString())

            // 第二次序列化
            val redundance = RedundanceProto.Redundance.newBuilder()
                .setContent(messageProto)
                .setDevice("phone")
                .setMsgType(RedundanceProto.Redundance.MsgType.valueOf(message.messageType))
                .setSender(message.sender)
                .setReceiver(message.receiver)
                .build().toByteArray()

            socket.emit("send-msg", redundance, Ack {
                val result = it[0]
                if(result == "send-ack") {
                    messageHandler.onSendAck()
                } else if(result == "send-error") {
                    messageHandler.onSendError()
                }
            })
        } catch (e: Exception) {
            Log.e(TAG, e.message!!)
        }
    }
}