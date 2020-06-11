package com.knilim.knilim.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.knilim.knilim.data.model.IUser.User

class MainViewModel : ViewModel() {

    private val _user = MutableLiveData<User>().apply {
        value = User(
            "49301c7b-3891-4a64-8436-a67777685042",
            "燕公",
            "gongsunce@gmail.com",
            "18890123456",
            "1234567890",
            "http://cdn.loheagn.com/2020-06-02-12CEBEBD-0E57-41A9-91E9-2ADA8AC0B3AF.jpg",
            "前进吧,达瓦里希!",
            true,
            "Beijing",
            "8888888"
        )
    }
    val user : LiveData<User> = _user
}