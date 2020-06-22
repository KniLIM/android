package com.knilim.knilim.ui.group

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.knilim.knilim.data.login.LoginRepository
import com.knilim.knilim.data.main.GroupRepository
import com.knilim.knilim.data.model.user.Group
import java.util.concurrent.CopyOnWriteArrayList

class GroupViewModel : ViewModel() {
    private val _groups = MutableLiveData<CopyOnWriteArrayList<Group>>().apply {
        GroupRepository.setGroupMap(LoginRepository.groups)
        value = LoginRepository.groups
    }
    val groups: LiveData<CopyOnWriteArrayList<Group>> = _groups
}