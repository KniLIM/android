package com.knilim.knilim.data.main

import com.knilim.knilim.data.model.dialog.DialogType
import com.stfalcon.chatkit.commons.models.IUser

object IUserRepository {

    /**
     * 通过ID，找到好友或群组
     */
    fun getIUserById(id: String): IUser {
        return FriendRepository.getFriendById(id) ?: GroupRepository.getGroupById(id)!!
    }

    fun judgeDialogType(id: String): Int {
        return if (GroupRepository.getGroupById(id) == null) {
            DialogType.P2P
        } else {
            DialogType.P2G
        }
    }
}