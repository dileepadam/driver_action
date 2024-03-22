package com.damc.driver_action.domain

interface PreferenceRepository {
    fun saveUsername(username: String)

    fun getUsername(): String
}