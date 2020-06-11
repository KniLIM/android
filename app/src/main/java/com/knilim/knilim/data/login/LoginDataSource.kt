package com.knilim.knilim.data.login

import com.knilim.knilim.data.model.IUser.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.withContext
import java.io.IOException

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
class LoginDataSource : CoroutineScope by MainScope() {

    fun login(username: String, password: String): Result<User> {
        try {
            // TODO: handle loggedInUser authentication
            val fakeUser = fetchLoginData("hehe", "nana")
            return Result.Success(fakeUser)
        } catch (e: Throwable) {
            return Result.Error(
                IOException(
                    "Error logging in",
                    e
                )
            )
        }
    }

    private fun fetchLoginData(username: String, password: String) = User(
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




    fun logout() {
        // TODO: revoke authentication
    }
}

