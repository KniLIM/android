package com.knilim.knilim.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.knilim.knilim.data.login.LoginRepository
import com.knilim.knilim.data.model.IUser.User

class MainViewModel : ViewModel() {

    private val _user = MutableLiveData<User>().apply {
        value = LoginRepository.user
    }
    val user : LiveData<User> = _user
}