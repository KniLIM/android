package com.knilim.knilim.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.knilim.base.Utils
import com.knilim.knilim.data.login.LoginRepository
import com.knilim.knilim.data.main.*
import com.knilim.knilim.data.model.dialog.Dialog
import com.knilim.knilim.data.model.message.Message
import com.knilim.knilim.data.model.user.Friend
import com.knilim.knilim.data.model.user.Group
import com.knilim.knilim.data.model.user.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.concurrent.CopyOnWriteArrayList

class MainViewModel : ViewModel() {

    private val _user = MutableLiveData<User>().apply {
        value = LoginRepository.user
    }
    val user: LiveData<User> = _user

    private val _groups = MutableLiveData<CopyOnWriteArrayList<Group>>().apply {
        GroupRepository.setGroupMap(LoginRepository.groups)
        value = LoginRepository.groups
    }
    val groups: LiveData<CopyOnWriteArrayList<Group>> = _groups

    private val _friends = MutableLiveData<CopyOnWriteArrayList<Friend>>().apply {
        FriendRepository.setFriendMap(LoginRepository.friends)
        value = LoginRepository.friends
    }
    val friends: LiveData<CopyOnWriteArrayList<Friend>> = _friends

    private val _dialogs = MutableLiveData<MutableList<Dialog>>()
    val dialogs: LiveData<MutableList<Dialog>> = _dialogs

    fun getDialogs() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val dialogList = DialogRepository.getDialogs()
                DialogRepository.initDialogMap(dialogList)
                _dialogs.postValue(dialogList)
            }
        }
    }
}