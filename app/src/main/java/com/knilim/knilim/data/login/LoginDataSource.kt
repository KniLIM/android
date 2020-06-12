package com.knilim.knilim.data.login

import com.knilim.base.Utils
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject
import okhttp3.RequestBody.Companion.toRequestBody

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
class LoginDataSource {

    fun login(username: String, password: String): Result<String> {
        return try {
            val fakeUser = fetchLoginData(username, password)
            Result.Success(fakeUser!!)
        } catch (e: Throwable) {
            Result.Error(
                Exception(
                    "Error logging in",
                    e
                )
            )
        }
    }

    private fun fetchLoginData(username: String, password: String): String? {
        val jsonBody = JSONObject()
        jsonBody.put("account", username)
        jsonBody.put("password", password)
        jsonBody.put("device", "phone")
        val requestBody = jsonBody.toString().toRequestBody(Utils.JSON)
        val url = Utils.getUrl("account/login")
        val request = Request.Builder()
            .url(url)
            .post(requestBody)
            .build()
        var response = OkHttpClient().newCall(request).execute().body?.string()
        if(response != null && ! JSONObject(response).getBoolean("success")) {
            response = null
        }
        return response
    }




    fun logout() {

    }
}

