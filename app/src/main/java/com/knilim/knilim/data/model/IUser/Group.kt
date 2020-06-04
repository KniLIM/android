package com.knilim.knilim.data.model.IUser

import com.stfalcon.chatkit.commons.models.IUser

data class Group(
    val groupId: String,
    val groupName: String,
    val gAvatar: String
) : IUser {

    val members : MutableList<IUser> = mutableListOf()

    override fun getAvatar(): String {
        return gAvatar
    }

    override fun getName(): String {
        return groupName
    }

    override fun getId(): String {
        return groupId
    }
}