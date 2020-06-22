package com.knilim.knilim.data.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.knilim.knilim.data.model.dialog.Dialog
import com.knilim.knilim.data.model.message.Message
import kotlinx.coroutines.*

object DialogManager : CoroutineScope by MainScope()  {
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
}