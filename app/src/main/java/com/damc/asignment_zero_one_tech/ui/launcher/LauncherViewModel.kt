package com.damc.asignment_zero_one_tech.ui.launcher

import android.content.Context
import androidx.lifecycle.viewModelScope
import com.damc.asignment_zero_one_tech.app.AssignmentApplication
import com.damc.asignment_zero_one_tech.domain.LocalRepostories
import com.damc.asignment_zero_one_tech.ui.BaseViewModel
import com.damc.asignment_zero_one_tech.utils.Utils.Companion.showToast
import kotlinx.coroutines.launch

class LauncherViewModel(val database: LocalRepostories) : BaseViewModel() {

    fun loginToRegister() {
        navigate(LauncherFragmentDirections.actionLoginToRegister())
    }

    fun loginToHome() {
        navigate(LauncherFragmentDirections.actionLoginToHome())
    }

    suspend fun isUserDetailsOk(username: String, password: String): Boolean {
        val user = database.userLogin(username, password)
        if (user != null) {
            AssignmentApplication().setLoginUser(user)
        }
        return user != null
    }

    fun validateInputs(username: String, password: String, context: Context) {
        viewModelScope.launch {
            if (username.isEmpty() || password.isEmpty()) {
                showToast("Fields cannot be empty", context)
            } else if (isUserDetailsOk(username, password)) {
                showToast("Login Successful", context)
                loginToHome()
            } else {
                showToast("Invalid Credentials", context)
            }
        }
    }

}