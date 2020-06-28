package com.knilim.knilim.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.knilim.knilim.data.login.LoginRepository
import com.knilim.knilim.data.main.*
import com.knilim.knilim.data.model.user.Friend
import com.knilim.knilim.data.model.user.Group
import com.knilim.knilim.data.model.user.User
import com.knilim.server.MessageServer
import java.util.concurrent.CopyOnWriteArrayList

class MainViewModel : ViewModel() {

    private val _user = MutableLiveData<User>().apply {
        value = LoginRepository.user
    }
    val user: LiveData<User> = _user

    fun connectSessionServer() {
        MessageServer.config(LoginRepository.socket.first, LoginRepository.socket.second, LoginRepository.token, LoginRepository.user.id, "phone", DialogManager)
        MessageServer.connect()
    }

    private val _friends = MutableLiveData<CopyOnWriteArrayList<Friend>>().apply {
        FriendRepository.setFriendMap(LoginRepository.friends)
        value = LoginRepository.friends
    }
    val friends: LiveData<CopyOnWriteArrayList<Friend>> = _friends

    private val _groups = MutableLiveData<CopyOnWriteArrayList<Group>>().apply {
        GroupRepository.setGroupMap(LoginRepository.groups)
        value = LoginRepository.groups
    }
    val groups: LiveData<CopyOnWriteArrayList<Group>> = _groups

    override fun onCleared() {
        super.onCleared()

        MessageServer.disConnect()
    }
}