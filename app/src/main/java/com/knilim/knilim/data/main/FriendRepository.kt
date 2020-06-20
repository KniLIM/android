package com.knilim.knilim.data.main

import com.knilim.knilim.data.model.user.Friend
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.CopyOnWriteArrayList

object FriendRepository {
    private val friendMap = ConcurrentHashMap<String, Friend>()
    lateinit var friends: CopyOnWriteArrayList<Friend>

    fun setFriendMap(friends: CopyOnWriteArrayList<Friend>) {
        this.friends = friends
        for(friend in friends) {
            friendMap[friend.id] = friend
        }
    }

    fun getFriendById(id : String) : Friend? {
        return friendMap[id]
    }
}