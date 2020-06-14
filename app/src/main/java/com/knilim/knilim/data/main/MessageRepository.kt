package com.knilim.knilim.data.main

import android.util.Log
import com.knilim.knilim.data.model.dialog.DialogType
import com.knilim.knilim.data.model.message.Message
import com.knilim.knilim.data.model.message.MessageType
import com.stfalcon.chatkit.commons.models.IMessage
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.CopyOnWriteArrayList

object MessageRepository {
    private val messageMap = ConcurrentHashMap<String, CopyOnWriteArrayList<Message>>()

    fun updateMessageMap(messages: List<Message>) {
        Log.d("message update", "message update")
        for(message in messages) {
            if(messageMap[message.dialogId] == null) {
                messageMap[message.dialogId] = CopyOnWriteArrayList()
            }
            messageMap[message.dialogId]?.add(message)
        }
    }

    fun getLastMessage(dialogId: String): Message? {
        return messageMap[dialogId]?.last()
    }

    fun judgeMessageType(dialogId: String): Int {
        return if(IUserRepository.judgeDialogType(dialogId) == DialogType.P2G) {
            MessageType.P2G
        } else {
            MessageType.P2P
        }
    }
}