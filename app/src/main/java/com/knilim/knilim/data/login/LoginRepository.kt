package com.knilim.knilim.data.login

import com.knilim.knilim.data.model.user.User
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import com.knilim.base.Utils
import com.knilim.knilim.data.model.user.Friend
import com.knilim.knilim.data.model.user.Group
import com.knilim.server.data.model.Socket

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */

object LoginRepository {
    
    private val dataSource = LoginDataSource()
    
    lateinit var user : User
    lateinit var socket: Socket
    lateinit var friends : MutableList<Friend>
    lateinit var groups : MutableList<Group>
    lateinit var token : String

    val isLoggedIn: Boolean
        get() = user != null


    fun logout() {
        dataSource.logout()
    }

    suspend fun login(username: String, password: String): Result<String> {
        val result = dataSource.login(username, password)

        return if (result is Result.Success) {
            val response = Utils.gson.fromJson(result.data, JsonObject::class.java)
            user = Utils.gson.fromJson(response.get("self").toString(), User::class.java)
            var turnsType = object : TypeToken<List<Group>>() {}.type
            groups = Utils.gson.fromJson(response.get("groups").toString(), turnsType)
            turnsType = object : TypeToken<List<Friend>>() {}.type
            friends = Utils.gson.fromJson(response.get("friends").toString(), turnsType)
            socket = Utils.gson.fromJson(response.get("socket").toString(), Socket::class.java)
            token = Utils.gson.fromJson(response.get("token").toString(), String::class.java)
            // 为了契合后端的bug...
            user.nickname = Utils.gson.fromJson(response.getAsJsonObject("self").get("nickName").toString(), String::class.java)
            Result.Success(user.nickname)
        } else {
            result
        }
    }
}