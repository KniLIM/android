package com.knilim.knilim.data.model.message

import com.stfalcon.chatkit.commons.models.IMessage
import com.stfalcon.chatkit.commons.models.IUser
import java.util.*

data class Message(
    val fromUser: IUser,
    val toUser: IUser,
    val description: String,
    val content: String,    // 消息的具体内容, 对于文件和图片来说,就是存储在本地的地址, 对于文本消息来说,就是消息本身,这时其值与description相同
    val createTime: Date,
    val messageType: MessageType
) : IMessage {

    private val messageId : String = UUID.randomUUID().toString()

    override fun getId(): String {
        return messageId
    }

    override fun getCreatedAt(): Date {
        return createTime
    }

    override fun getUser(): IUser {
        return fromUser
    }

    override fun getText(): String {
        return if(messageType == MessageType.TEXT) {
            content
        } else {
            description
        }
    }
}