package com.knilim.knilim.ui.chat

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.knilim.base.Utils
import com.knilim.knilim.data.model.message.Message
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ChatViewModel: ViewModel() {

    lateinit var messages: LiveData<List<Message>>

    fun setMessagesLiveData(dialogId: String) {
        messages = Utils.db.messageDao().getMessagesByDialogId(dialogId)
    }

}