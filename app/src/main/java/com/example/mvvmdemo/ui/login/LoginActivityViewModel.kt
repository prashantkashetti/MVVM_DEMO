package com.example.mvvmdemo.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mvvmdemo.R
import com.example.mvvmdemo.models.LoginModel

class LoginActivityViewModel : ViewModel() {

    private val _usernameError = MutableLiveData<Int?>()
    val usernameError: LiveData<Int?>
        get() = _usernameError

    private val _passwordError = MutableLiveData<Int?>()
    val passwordError: LiveData<Int?>
        get() = _passwordError

    private val _isLoginButtonEnabled = MutableLiveData(false)
    val isLoginButtonEnabled: LiveData<Boolean>
        get() = _isLoginButtonEnabled

    private var _username: String? = null
    private var _password: String? = null

    fun onUsernameChanged(viewId: Int, charSequence: CharSequence?) {
        _username = charSequence.toString()
        if (_username.isNullOrEmpty()) {
            _usernameError.value = R.string.username_error
        } else {
            _usernameError.value = null
        }
        updateLoginButtonStatus()
    }

    fun onPasswordChanged(viewId: Int, charSequence: CharSequence?) {
        _password = charSequence.toString()
        if (_password.isNullOrEmpty()) {
            _passwordError.value = R.string.password_error
        } else {
            _passwordError.value = null
        }
        updateLoginButtonStatus()
    }

    private fun updateLoginButtonStatus() {
        _isLoginButtonEnabled.value = _username.isNullOrEmpty().not() && _password.isNullOrEmpty().not()
    }

    fun onLoginClicked() = LoginModel(_username, _password)
}