package com.knilim.knilim.data.main

import android.util.Log
import com.knilim.knilim.data.model.dialog.DialogType
import com.knilim.knilim.data.model.message.Message
import com.knilim.knilim.data.model.message.MessageType
import com.stfalcon.chatkit.commons.models.IMessage
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.CopyOnWriteArrayList

object MessageRepository {
    private val messageMap = ConcurrentHashMap<String, Message>()

    fun initMessageMap(messages: List<Message>) {
        Log.d("message update", "message update")
        for (message in messages) {
            messageMap[message.dialogId] = message
        }
    }

    fun updateMessageMap(dialogId: String, message: Message) {
        messageMap[dialogId] = message
    }

    fun getLastMessage(dialogId: String): Message? {
        return messageMap[dialogId]
    }

    fun judgeMessageType(dialogId: String): Int {
        return if (IUserRepository.judgeDialogType(dialogId) == DialogType.P2G) {
            MessageType.P2G
        } else {
            MessageType.P2P
        }
    }
}