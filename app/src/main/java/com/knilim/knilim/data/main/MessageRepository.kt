package com.knilim.knilim.data.main

import android.util.Log
import com.knilim.knilim.data.model.message.Message
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

    fun getLastMessage(dialogId: String): IMessage? {
        return messageMap[dialogId]?.last()
    }
}