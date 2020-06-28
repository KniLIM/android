package com.knilim.knilim.data.main

import com.knilim.base.Utils
import com.knilim.knilim.data.model.dialog.Dialog
import com.knilim.knilim.data.model.message.Message
import kotlinx.coroutines.*
import java.util.concurrent.ConcurrentHashMap

object DialogRepository : CoroutineScope by MainScope() {
    private val dialogMap = ConcurrentHashMap<String, Dialog>()
    fun initDialogMap(dialogs: List<Dialog>) {
        for (dialog in dialogs) {
            dialogMap[dialog.id] = dialog
        }
    }

    fun getDialogs(): MutableList<Dialog> {
        val dialogs = Utils.db.dialogDao().selectAllDialogs()
        val messages = Utils.db.messageDao().selectAllMessages()
        MessageRepository.initMessageMap(messages)
        val result = mutableListOf<Dialog>()
        for (dialog in dialogs) {
            dialog.lastMessage = MessageRepository.getLastMessage(dialog.id)
            result.add(dialog)
        }
        // 对result列表，按照最后一次消息的发送时间排个序
        result.sortByDescending {
            if(it.lastMessage == null) {
                it.createdTime
            } else {
                it.lastMessage.createdTime
            }
        }
        return result
    }

    fun getOrCreateDialogById(id: String, message: Message? = null): Dialog {
        return if(dialogMap[id] == null) {
            dialogMap[id] = createDialog(id, message)
            dialogMap[id]!!
        } else {
            launch {
                withContext(Dispatchers.IO) {
                    message?.let {
                        Utils.db.messageDao().insertMessage(message)
                    }
                }
            }
            dialogMap[id]!!
        }
    }

    private fun createDialog(id: String, message: Message? = null): Dialog {
        val user = IUserRepository.getIUserById(id)
        val dialog = Dialog(
            id,
            IUserRepository.judgeDialogType(id),
            user.avatar,
            0,
            user.name,
            System.currentTimeMillis()
        )
        launch {
            withContext(Dispatchers.IO) {
                Utils.db.dialogDao().insertDialog(dialog)
                message?.let { Utils.db.messageDao().insertMessage(it) }
            }
        }
        return dialog
    }
}