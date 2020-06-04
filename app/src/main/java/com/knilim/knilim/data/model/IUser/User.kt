package com.knilim.knilim.data.model.IUser

import com.stfalcon.chatkit.commons.models.IUser

data class User(
    val userId: String,
    val nickname: String,
    val email: String,
    val phone: String,
    val password: String,
    val mAvatar: String,
    val signature: String
) : IUser {

    override fun getAvatar(): String {
        return mAvatar
    }


    override fun getId(): String {
        return userId
    }

    override fun getName(): String {
        return nickname
    }
}
