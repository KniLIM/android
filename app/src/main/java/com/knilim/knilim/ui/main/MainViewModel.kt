package com.knilim.knilim.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.knilim.knilim.data.login.LoginRepository
import com.knilim.knilim.data.main.FriendRepository
import com.knilim.knilim.data.main.GroupRepository
import com.knilim.knilim.data.model.user.Friend
import com.knilim.knilim.data.model.user.Group
import com.knilim.knilim.data.model.user.User

class MainViewModel : ViewModel() {

    private val _user = MutableLiveData<User>().apply {
        value = LoginRepository.user
    }
    val user: LiveData<User> = _user

    private val _groups = MutableLiveData<MutableList<Group>>().apply {
        value = LoginRepository.groups
    }
    val groups: LiveData<MutableList<Group>> = _groups

    private val _friends = MutableLiveData<MutableList<Friend>>().apply {
        value = LoginRepository.friends
    }
    val friends: LiveData<MutableList<Friend>> = _friends

    private val socket = LoginRepository.socket
    private val token = LoginRepository.token

    init {
        GroupRepository.groups = LoginRepository.groups
        FriendRepository.friends = LoginRepository.friends
    }
}