package com.knilim.knilim.data.model.dialog

import com.knilim.server.data.model.message.Message
import com.stfalcon.chatkit.commons.models.IDialog
import com.stfalcon.chatkit.commons.models.IMessage
import com.stfalcon.chatkit.commons.models.IUser

class Dialog : IDialog<IMessage> {



    override fun getDialogPhoto(): String {
        TODO("Not yet implemented")
    }

    override fun getUnreadCount(): Int {
        TODO("Not yet implemented")
    }

    override fun setLastMessage(message: IMessage?) {
        TODO("Not yet implemented")
    }

    override fun getId(): String {
        TODO("Not yet implemented")
    }

    override fun getUsers(): MutableList<out IUser> {
        TODO("Not yet implemented")
    }


    override fun getDialogName(): String {
        TODO("Not yet implemented")
    }

    override fun getLastMessage(): Message {
        TODO("Not yet implemented")
    }
}