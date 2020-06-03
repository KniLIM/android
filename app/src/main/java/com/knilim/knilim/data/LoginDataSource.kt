package com.knilim.knilim.data

import com.knilim.knilim.data.model.User
import java.io.IOException

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
class LoginDataSource {

    fun login(username: String, password: String): Result<User> {
        try {
            // TODO: handle loggedInUser authentication
            val fakeUser = User("49301c7b-3891-4a64-8436-a67777685042",
                "燕公",
                "gongsunce@gmail.com",
                "18890123456",
                "1234567890",
                "http://cdn.loheagn.com/2020-06-02-12CEBEBD-0E57-41A9-91E9-2ADA8AC0B3AF.jpg",
                "前进吧,达瓦里希!")
            return Result.Success(fakeUser)
        } catch (e: Throwable) {
            return Result.Error(IOException("Error logging in", e))
        }
    }

    fun logout() {
        // TODO: revoke authentication
    }
}

