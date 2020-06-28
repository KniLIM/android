package com.knilim.knilim.data.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.knilim.base.Utils
import com.knilim.knilim.data.model.dialog.Dialog
import com.knilim.knilim.data.model.message.Message
import com.knilim.server.MessageHandler
import kotlinx.coroutines.*

object DialogManager : CoroutineScope by MainScope(),
    MessageHandler {
    private val _lastMessage = MutableLiveData<Message>()
    val lastMessage : LiveData<Message> = _lastMessage

    fun updateLastMessage(message: Message) {
        _lastMessage.value = message
    }

    private var isDialogsLoaded: Boolean = false

    private val _dialogs = MutableLiveData<MutableList<Dialog>>()
    val dialogs: LiveData<MutableList<Dialog>> = _dialogs

    fun getDialogs() {
        if(!isDialogsLoaded) {
            launch {
                withContext(Dispatchers.IO) {
                    val dialogList = DialogRepository.getDialogs()
                    DialogRepository.initDialogMap(dialogList)
                    _dialogs.postValue(dialogList)
                    isDialogsLoaded = true
                }
            }
        }
    }

    private val _connectResponse = MutableLiveData<String>()
    val connectResponse: LiveData<String> = _connectResponse

    private val _onSend = MutableLiveData<String>()
    val onSend : LiveData<String> = _onSend

    private val _rsvMessage = MutableLiveData<Message>()
    val rsvMessage: LiveData<Message> = _rsvMessage

    override fun onMessageRcv(message: Message) {
        DialogRepository.getOrCreateDialogById(message.dialogId, message)
        _rsvMessage.postValue(message)
        // 更新lastMessage
        _lastMessage.postValue(message)
    }


    override fun onConnectAck() {
        _connectResponse.postValue("session-server connect successfully")
    }

    override fun onSendAck() {
        _onSend.postValue("send-ack")
    }

    override fun onConnectError() {
        _connectResponse.postValue("session-server connect failed")
    }

    override fun onSendError() {
        _onSend.postValue("send-error")
    }
}