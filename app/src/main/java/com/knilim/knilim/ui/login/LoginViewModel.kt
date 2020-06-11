package com.knilim.knilim.ui.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import android.util.Patterns
import com.knilim.base.BaseViewModel
import com.knilim.knilim.data.login.LoginRepository
import com.knilim.knilim.data.login.Result

import com.knilim.knilim.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LoginViewModel(private val loginRepository: LoginRepository) : BaseViewModel() {

    private val _loginForm = MutableLiveData<LoginFormState>()
    val loginFormState: LiveData<LoginFormState> = _loginForm

    private val _loginResult = MutableLiveData<LoginResult>()
    val loginResult: LiveData<LoginResult> = _loginResult

    fun login(username: String, password: String) {
        // can be launched in a separate asynchronous job
        Log.d("threadtest", Thread.currentThread().toString())
        uiScope.launch {
            val deferred = async(Dispatchers.IO) {
                delay(60000)
                loginRepository.login(username, password)
            }
            val result = deferred.await()
            if (result is Result.Success) {
                _loginResult.value = LoginResult(success = LoggedInUserView(displayName = result.data.nickname))
            } else {
                _loginResult.value = LoginResult(error = R.string.login_failed)
            }
        }
    }

    fun loginDataChanged(username: String, password: String) {
        if (!isUserNameValid(username)) {
            _loginForm.value = LoginFormState(usernameError = R.string.invalid_username)
        } else if (!isPasswordValid(password)) {
            _loginForm.value = LoginFormState(passwordError = R.string.invalid_password)
        } else {
            _loginForm.value = LoginFormState(isDataValid = true)
        }
    }

    private fun isUserNameValid(username: String): Boolean {
        return if (username.contains('@')) {
            Patterns.EMAIL_ADDRESS.matcher(username).matches()
        } else {
            Patterns.PHONE.matcher(username).matches()
        }
    }

    private fun isPasswordValid(password: String): Boolean {
        return password.isNotBlank()
    }
}
