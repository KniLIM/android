package com.knilim.knilim.data.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.knilim.knilim.data.model.message.Message

object DialogManager {
    private val _lastMessage = MutableLiveData<Message>()
    val lastMessage : LiveData<Message> = _lastMessage

    fun updateLastMessage(message: Message) {
        _lastMessage.value = message
    }
}