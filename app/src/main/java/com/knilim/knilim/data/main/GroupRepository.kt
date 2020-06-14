package com.knilim.knilim.data.main

import com.knilim.knilim.data.model.user.Group
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.CopyOnWriteArrayList

object GroupRepository {

    private val groupMap = ConcurrentHashMap<String, Group>()

    fun setGroupMap(groups : CopyOnWriteArrayList<Group>) {
        for(group in groups) {
            groupMap[group.id] = group
        }
    }

    fun getGroupById(id: String): Group? {
        return groupMap[id]
    }
}