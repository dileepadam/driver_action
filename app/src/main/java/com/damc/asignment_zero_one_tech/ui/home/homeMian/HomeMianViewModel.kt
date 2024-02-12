package com.damc.asignment_zero_one_tech.ui.home.homeMian

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.damc.asignment_zero_one_tech.app.AssignmentApplication
import com.damc.asignment_zero_one_tech.domain.LocalRepostories
import com.damc.asignment_zero_one_tech.ui.BaseViewModel
import kotlinx.coroutines.launch

class HomeMianViewModel(val dataBase: LocalRepostories) : BaseViewModel() {

    var favouriteCount: MutableLiveData<Int> = MutableLiveData<Int>()

    private suspend fun getFavouriteBookCount1(userId: Int) {
        favouriteCount.postValue(dataBase.getFavouriteBookCount(userId))
    }

    fun getFavouriteBookCount(userId: Int) {
        viewModelScope.launch {
            getFavouriteBookCount1(userId)
        }
    }
}