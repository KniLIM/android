package com.knilim.knilim.ui.chat

import androidx.lifecycle.*
import com.knilim.base.Utils
import com.knilim.knilim.data.main.DialogRepository
import com.knilim.knilim.data.model.dialog.Dialog
import com.knilim.knilim.data.model.message.Message
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ChatViewModel() : ViewModel() {

    private val _messages = MutableLiveData<List<Message>>()
    val messages: LiveData<List<Message>> = _messages

    fun getMessagesByDialogId(dialogId: String) {
         viewModelScope.launch {
             withContext(Dispatchers.IO) {
                 _messages.postValue(Utils.db.messageDao().getMessagesByDialogId(dialogId))
             }
         }
    }

    fun getDialogById(dialogId: String): Dialog {
        return DialogRepository.getOrCreateDialogById(dialogId)
    }

    fun insertMessage(message: Message) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                Utils.db.messageDao().insertMessage(message)
            }
        }
    }
}