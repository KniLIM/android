package com.knilim.knilim.ui.friend

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.knilim.knilim.data.login.LoginRepository
import com.knilim.knilim.data.main.FriendRepository
import com.knilim.knilim.data.model.user.Friend
import java.util.concurrent.CopyOnWriteArrayList

class FriendViewModel : ViewModel() {
    private val _friends = MutableLiveData<CopyOnWriteArrayList<Friend>>().apply {
        FriendRepository.setFriendMap(LoginRepository.friends)
        value = LoginRepository.friends
    }
    val friends: LiveData<CopyOnWriteArrayList<Friend>> = _friends
}