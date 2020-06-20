package com.knilim.knilim.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.knilim.base.Utils
import com.knilim.knilim.data.login.LoginRepository
import com.knilim.knilim.data.main.FriendRepository
import com.knilim.knilim.data.main.GroupRepository
import com.knilim.knilim.data.main.MessageRepository
import com.knilim.knilim.data.main.UserRepository
import com.knilim.knilim.data.model.dialog.Dialog
import com.knilim.knilim.data.model.dialog.DialogType
import com.knilim.knilim.data.model.message.ContentType
import com.knilim.knilim.data.model.message.Message
import com.knilim.knilim.data.model.message.MessageType
import com.knilim.knilim.data.model.user.Friend
import com.knilim.knilim.data.model.user.Group
import com.knilim.knilim.data.model.user.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*
import java.util.concurrent.CopyOnWriteArrayList

class MainViewModel : ViewModel() {

    private val _user = MutableLiveData<User>().apply {
        value = LoginRepository.user
    }
    val user: LiveData<User> = _user

    private val _groups = MutableLiveData<CopyOnWriteArrayList<Group>>().apply {
        value = LoginRepository.groups
    }
    val groups: LiveData<CopyOnWriteArrayList<Group>> = _groups

    private val _friends = MutableLiveData<CopyOnWriteArrayList<Friend>>().apply {
        value = LoginRepository.friends
    }
    val friends: LiveData<CopyOnWriteArrayList<Friend>> = _friends

    lateinit var messages: LiveData<List<Message>>
    lateinit var dialogs: LiveData<List<Dialog>>

    private val socket = LoginRepository.socket
    private val token = LoginRepository.token

    init {
        GroupRepository.setGroupMap(LoginRepository.groups)
        FriendRepository.setFriendMap(LoginRepository.friends)
        UserRepository.user = LoginRepository.user
        messages = Utils.db.messageDao().selectAllMessages()
        dialogs = Utils.db.dialogDao().selectAllDialogs()
//        initDatabase()
    }

    fun updateMessagesMap(messages: List<Message>) {
        MessageRepository.setMessageMap(messages)
    }

    fun updateDialogMap(dialogs: List<Dialog>) {

    }

    fun initDatabase() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val dialogId = LoginRepository.friends.last().id
                val dialog = Dialog(
                    dialogId,
                    DialogType.P2P,
                    "http://cdn.loheagn.com/2020-06-14-Snipaste_2020-06-14_16-42-18.png",
                    1,
                    "雷猴王",
                    System.currentTimeMillis()
                )
                Utils.db.dialogDao().insertDialog(dialog)
                val message = Message(
                    UUID.randomUUID().toString(),
                    MessageType.P2P,
                    ContentType.TEXT,
                    "5e809c0a-d986-4f6d-b672-95314fe7ca85",
                    "02a8d545-5b2b-40da-93dd-c9e20e0d21fb",
                    System.currentTimeMillis(),
                    "嘿，小伙子，想买我的手机吗",
                    dialogId
                )
                Utils.db.messageDao().insertMessage(message)
            }
        }
    }
}