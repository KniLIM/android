package com.knilim.knilim.data.main

import com.knilim.knilim.data.model.dialog.Dialog
import java.util.concurrent.ConcurrentHashMap

object DialogRepository {
    private val dialogMap = ConcurrentHashMap<String, Dialog>()
    fun initDialogMap(dialogs: List<Dialog>) {
        for (dialog in dialogs) {
            dialogMap[dialog.id] = dialog
        }
    }

    fun getDialogById(id: String): Dialog {
        var result = dialogMap[id]
        val user = IUserRepository.getIUserById(id)
        if (result == null) {
            result = Dialog(
                id,
                IUserRepository.judgeDialogType(id),
                user.avatar,
                0,
                user.name,
                System.currentTimeMillis()
            )
            dialogMap[id] = result
        }
        return result
    }
}