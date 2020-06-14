package com.knilim.knilim.ui.chat

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.knilim.base.Utils
import com.knilim.knilim.data.model.message.Message
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ChatViewModel: ViewModel() {
    fun insertMessage(message: Message) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                Utils.db.messageDao().insertMessage(message)
            }
        }
    }
}