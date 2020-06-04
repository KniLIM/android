package com.knilim.knilim.data.model.IUser

import com.stfalcon.chatkit.commons.models.IUser

data class Friend(
    val userId: String,
    val nickname: String,  // 好友实际的昵称
    val addonName: String,  // 备注名
    val fAvatar: String
) : IUser {
    override fun getAvatar(): String {
        return fAvatar
    }

    override fun getName(): String {
        return addonName
    }

    override fun getId(): String {
        return userId
    }
}