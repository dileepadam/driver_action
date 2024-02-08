package com.damc.asignment_zero_one_tech.utils

import android.content.Context
import android.widget.Toast

class Utils {
    companion object{

        fun validateEmail(email: String): Boolean {
            val emailRegex = Regex("^\\S+@\\S+\\.\\S+\$")
            return emailRegex.matches(email)
        }

        fun showToast(message: String, context: Context) {
            Toast.makeText(context, message, Toast.LENGTH_LONG).show()
        }
    }
}