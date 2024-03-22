package com.damc.driver_action.data.local

import android.content.SharedPreferences
import com.damc.driver_action.domain.PreferenceRepository

class PreferenceRepositoryImpl(private val sharedPreferences: SharedPreferences) :
    PreferenceRepository {

    private val FileKey = "com.damc.driver.a.x"
    private val USERNAME = FileKey + "a"

    private fun retrieveString(key: String): String {
        return sharedPreferences.getString(key, "")!!
    }

    private fun storeString(key: String, value: String) {
        with(sharedPreferences.edit()) {
            putString(key, value)
            apply()
        }
    }


    private fun storeBoolean(key: String, value: Boolean) {
        with(sharedPreferences.edit()) {
            putBoolean(key, value)
            apply()
        }
    }

    private fun retrieveBoolean(key: String): Boolean {
        return sharedPreferences.getBoolean(key, false)
    }

    override fun saveUsername(username: String) {
        storeString(USERNAME, username)
    }

    override fun getUsername(): String {
        return retrieveString(USERNAME)
    }


}