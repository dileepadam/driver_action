package com.damc.asignment_zero_one_tech.ui.register

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.viewModelScope
import com.damc.asignment_zero_one_tech.domain.LocalRepostories
import com.damc.asignment_zero_one_tech.domain.models.Users
import com.damc.asignment_zero_one_tech.ui.BaseViewModel
import com.damc.asignment_zero_one_tech.utils.Utils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegisterViewModel(val dataBase: LocalRepostories) : BaseViewModel() {

    lateinit var users: Users
    fun registerToLogin() {
        navigateBack()
    }

    fun addUerToDb(users: Users) {
        viewModelScope.launch { dataBase.insertUser(users) }
    }

    suspend fun checkUsernameInDb(username: String): Int {
        return dataBase.isUsernameInDb(username)
    }

    suspend fun validateFields(
        username: String,
        email: String,
        password: String,
        confirmPassword: String,
        context: Context
    ) {
        if (username.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            showToast("Fields cannot be empty", context)
        } else if (!Utils.validateEmail(email)) {
            showToast("Invalid email", context)
        } else if (password != confirmPassword) {
            showToast("Password & Confirm Password should be match", context)
        } else if (checkUsernameInDb(username) > 0) {
            showToast("Username already taken", context)
        } else {
            users = Users(username, password)
            addUerToDb(users)// can use hashing for store password securely
            showToast("Successfully Registerer", context)
        }
    }

    fun showToast(message: String, context: Context) {
        CoroutineScope(Dispatchers.Main).launch {
            Toast.makeText(
                context,
                message,
                Toast.LENGTH_LONG
            ).show()
        }

    }


}